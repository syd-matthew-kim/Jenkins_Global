#!/usr/bin/env groovy

package au.com.petcircle.JenkinsPP.global

class instanceGroup {

    void createFolder(String current) {
        sh "mkdir -p ${current}/WEB-INF/classes"
    }

    void copyProperties(String service, String current) {
        sh "cp prod/${service}Logback.xml ${current}/WEB-INF/classes/logback.xml"
        sh "cp prod/${service}.properties ${current}/WEB-INF/classes/service.properties"
        sh "cp prod/${service}.context ${current}/META-INF/context.xml"
    }

    void updateVer(String service, String dest, String version, String today) {
        sh "sed -i \\\"s/${dest}.*/${dest}=${version},${today}/\\\" /opt/currentVersion/${service}"
    }

    String svrIP1, svrIP2, svrName1, svrName2
    void setSvrIPs(String service) {

        switch (${service}) {
            case "product" : case "review":
                svrIP1 = "10.50.10.21"
                svrIP2 = "10.50.10.22"
                svrName1 = "ig101"
                svrName2 = "ig102"
                break

            case "merch": case "order": case "search":
                svrIP1 = "10.50.10.31"
                svrIP2 = "10.50.10.32"
                svrName1 = "ig201"
                svrName2 = "ig202"
                break

            case "payment" : case "customer":
                svrIP1 = "10.50.10.41"
                svrIP2 = "10.50.10.42"
                svrName1 = "ig301"
                svrName2 = "ig302"
                break

            case "shipping":
                svrIP1 = "10.50.10.51"
                svrIP2 = "10.50.10.52"
                svrName1 = "ig401"
                svrName2 = "ig402"
                break
        }
    }

    String getSvrIP(String service, String svrNumber)
    {
        setSvrIPs(service)
        if (svrNumber == "1" )
        {
            return svrIP1
        }else
        {
            return svrIP2
        }
    }

    String getSvrName(String service, String svrNumber)
    {
        setSvrIPs(service)
        if (svrNumber == "1" )
        {
            return svrName1
        }else
        {
            return svrName2
        }
    }

}
