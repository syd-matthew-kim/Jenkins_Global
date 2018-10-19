#!/usr/bin/env groovy

package au.com.petcircle.JenkinsPP.global

class instanceGroup {

    String svrIP1, svrIP2, svrName1, svrName2
    void setSvrIPs(String service) {

        switch (service) {
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

    String getSvrIP(String service, String whereto)
    {
        setSvrIPs(service)
        if (whereto == "Alpha" )
        {
            return svrIP1
        }else if(whereto == "Beta")
        {
            return svrIP2
        }
    }

    String getSvrName(String service, String whereto)
    {
        setSvrIPs(service)
        if (whereto == "Alpha" )
        {
            return svrName1
        }else if (whereto == "Beta")
        {
            return svrName2
        }
    }

}
