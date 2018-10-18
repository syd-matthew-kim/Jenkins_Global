@Library('instanceGroup')_

import au.com.petcircle.JenkinsPP.global.instanceGroup

node{

    String SERVICENAME = "${params.service}".trim()
    String SERVICENAME1 = "${params.service1}".trim()
    String VERSION = "${params.version}".trim()
    String WORKSPACE = pwd()
    Date date = new Date()
    Date TODAY = date.format("yyyy-MM-dd HH-mm-ss")

    stage('Vallidation & Check Password') {
        if ("$SERVICENAME" != "$SERVICENAME1")
        {
            error("Check your serviceName")
        }
        withCredentials([string(credentialsId: 'PROD_ID', variable: 'E_secret')]) {
            if ("$E_secret" != "${params.password}") {
                error("Wrong Password")
            }
        }
        deleteDir()
    }

    stage('set up project service Account') {
        sh "gcloud config configurations activate devtest"
    }

    stage('Get Properties from GCP Repository'){
        git(
                url: 'https://source.developers.google.com/p/petcircle-devtest-cloud/r/Properties',
                credentialsId: 'source:DevTest',
                branch: "master"
        )
    }

    stage('Get Artifact from Nexus') {
        echo "$VERSION"
        artifactResolver artifacts: [artifact(artifactId: SERVICENAME, extension: 'war', groupId: 'au.com.petcircle', version: '$VERSION')], targetDirectory: 'target'
    }

    stage('Setup Configuration Files') {
        String ARTIFACT_PATH = sh(script: "ls target/*.war", returnStdout: true).trim()
        String ARTIFACT_PATH_FULL = "${WORKSPACE}/${ARTIFACT_PATH}"
        instanceGroup IG = new instanceGroup()

        echo "STEP 1. Create a Folder"
        IG.createFolder("${WORKSPACE}")
        echo "STEP 2. COPY properties, logback and Context.xml"
        IG.copyProperties("${SERVICENAME}", "${WORKSPACE}")
        echo "STEP 3. Update Jar files"
        updateJar(ARTIFACT_PATH_FULL)
        archiveArtifacts 'target/*.war*'
    }

    stage('Deploy.') {
        String WAR_PATH_RELATIVE = sh(script: "ls target/*.war", returnStdout: true).trim()
        String WAR_PATH_FULL = "${WORKSPACE}/${WAR_PATH_RELATIVE}"
        instanceGroup IG = new instanceGroup()
        String svrIP = IG.getSvrIP("${SERVICENAME}", "1")
        String svrName = IG.getSvrName("${SERVICENAME}", "1")

        withCredentials([usernamePassword(credentialsId: 'Manager_Tomcat', passwordVariable: 'manager_pass', usernameVariable: 'manager_id')]) {
            echo "Deploying application to http://${svrIP}/${SERVICENAME}"
            def output = deploy(WAR_PATH_FULL, svrIP, SERVICENAME, env.manager_id, env.manager_pass)
            if (output.contains("FAIL - Deployed application at context path / but context failed to start")) {
                echo "----- PUPPY deployment log -----"
                echo output
                echo "-------------------------------"
                currentBuild.result = 'FAILURE'
            }
        }

        echo "[END] Update Version Screen [END]"
        IG.updateVer("${SERVICENAME}", "${svrName}", "${VERSION}", TODAY)
    }
}

void updateJar(artifact){
    sh script:"cp ${artifact} ${artifact}.old", reutnrStdout: true
    sh script:"jar uvf ${artifact} WEB-INF/classes/", reutnrStdout: true
    sh script:"jar uvf ${artifact} META-INF/context.xml", reutnrStdout: true
}

String deploy(warPathFull, severIP, service, username, password) {
    String output = sh script: "curl --upload-file '${warPathFull}' " +
            "'http://${username}:${password}@${severIP}/manager/text/deploy" +
            "?path=/${service}&update=true'", returnStdout: true
    return output
}