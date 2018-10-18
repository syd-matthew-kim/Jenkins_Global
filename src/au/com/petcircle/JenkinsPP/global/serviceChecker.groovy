#!/usr/bin/env groovy

package au.com.petcircle.JenkinsPP.global

class serviceChecker {
    int min
    int max
    String baseName
    String baseIp

    void setValues(String serviceName){
        switch ("$serviceName"){
            case "shiba" :
                min = 10
                max = 19
                baseName = "shiba00"
                baseIp = "10.50.0.10"
                break
            case "minpin" :
                min = 10
                max = 19
                baseName = "minpin00"
                baseIp = "10.50.10.10"
                break
            case "product" :
                min = 20
                max = 29
                baseName = "labrador-product00"
                baseIp = "10.50.10.20"
                break
            case "payment" :
                min = 30
                max = 39
                baseName = "labrador-payment00"
                baseIp = "10.50.10.30"
                break
            case "customer":
                min = 40
                max = 49
                baseName = "labrador-customer00"
                baseIp = "10.50.10.40"
                break
            case "order":
                min = 50
                max = 59
                baseName = "labrador-order00"
                baseIp = "10.50.10.50"
                break
            case "merch":
                min = 60
                max = 69
                baseName = "labrador-merch00"
                baseIp = "10.50.10.60"
                break
            case "shipping":
                min = 70
                max = 79
                baseName = "labrador-shipping00"
                baseIp = "10.50.10.70"
                break
            case "search":
                min = 80
                max = 89
                baseName = "labrador-search00"
                baseIp = "10.50.10.80"
                break
            case "review":
                min = 90
                max = 99
                baseName = "labrador-review00"
                baseIp = "10.50.10.90"
                break
        }
    }

    int getMin(String serviceName) {
        setValues(serviceName)
        return min
    }
    int getMax(String serviceName) {
        setValues(serviceName)
        return max
    }
    String getBaseName(String serviceName) {
        setValues(serviceName)
        return baseName
    }
    String getBaseIp(String serviceName) {
        setValues(serviceName)
        return baseIp
    }

    String ins_num
    String ins_zone
    String ins_ip

    void checkInstances(String service){
        sh(script:"gcloud config configurations activate prod", returnStdout: false)
        sh(script:"gcloud compute instances list |grep ${service} |sort -u > current", reutnrStdout: true)
        sh(script:"cat current|awk '{print \$1}'|tail -1 |cut -d'-' -f2 > InsNum", reutnrStdout: false)
        sh(script:"cat current|awk '{print \$1}'|tail -1 > InsName", reutnrStdout: false)
        sh(script:"cat current|awk '{print \$2}'|tail -1 > InsZone", reutnrStdout: false)
        sh(script:"cat current|awk '{print \$4}'|tail -1 > InsIpaddr", reutnrStdout: false)
        ins_num = readFile('InsNum').trim()
        ins_zone = readFile('InsZone').trim()
        ins_ip = readFile('InsIpaddr').trim()
    }

    boolean isExist(String serviceName)
    {
        checkInstances(serviceName)
        String cur = readFile('current').trim()
        if ( cur.length() == 0 )
        {
            return false
        }else
        {
            return true
        }
    }

    String getInsNum()
    {
        return ins_num
    }
    String getCurIP()
    {
        return ins_ip
    }
    String getInsZone()
    {
        switch (ins_zone){
            case "australia-southeast1-a" :
                ins_zone = "australia-southeast1-b"
                break
            case "australia-southeast1-b" :
                ins_zone = "australia-southeast1-c"
                break
            case "australia-southeast1-c" :
                ins_zone = "australia-southeast1-a"
                break
        }
        return ins_zone
    }

    String getInsIP(min, max, ins_ip)
    {
        String Sins_ip = ins_ip
        int Imin = min
        int Imax = max

        int last = Integer.parseInt((Sins_ip.substring(Sins_ip.lastIndexOf(".") + 1))) + 1

        if (last <= Imax)
        {
            last = Math.min(Integer.parseInt((Sins_ip.substring(Sins_ip.lastIndexOf(".") + 1)))+1, Imax)
        }else {
            last = Imin
        }
        String new_ip = Sins_ip.substring(0, Sins_ip.lastIndexOf(".") + 1) + last
        return new_ip

    }

    String getInsName(service, ins_num)
    {
        ins_num = String.format("%02d", Integer.parseInt(ins_num.substring(service.length())))
        String svrName = "${service}${ins_num}"
        return svrName
    }

    void createInstance(String baseName, String baseIp)
    {
        sh(script:"gcloud compute instances create ${baseName} --private-network-ip=${baseIp} --zone=australia-southeast1-b --no-address --subnet=pc-prod-cloud-public-subnet-a-10-50-0-0-24 --source-instance-template=prod-medium-tomcat-fast-private-template-1 --labels=type=app")
    }

    void createInstance(String ins_name, String ins_zone, String ins_ip)
    {
        sh(script:"gcloud compute instances create ${ins_name} --private-network-ip=${ins_ip} --zone=${ins_zone} --no-address --subnet=pc-prod-cloud-public-subnet-a-10-50-0-0-24 --source-instance-template=prod-medium-tomcat-fast-private-template-1 --labels=type=app")
    }

}