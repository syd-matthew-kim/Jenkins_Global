#!/usr/bin/env groovy

package au.com.petcircle.JenkinsPP.global

class instanceGroup {

    String devIP1, devIP2, prodIP1, prodIP2, devName1, devName2, prodName1, prodName2
    void setSvrIPs(String service) {

        switch (service) {
            case "product" : case "review":
                devIP1 = "10.40.10.21"
                devIP2 = "10.40.10.22"
                prodIP1 = "10.50.10.21"
                prodIP2 = "10.50.10.22"
                devName1 = "ig101-dev"
                devName2 = "ig102-dev"
                prodName1 = "ig101"
                prodName2 = "ig102"
                break

            case "merch": case "order": case "search":
                devIP1 = "10.40.10.31"
                devIP2 = "10.40.10.32"
                prodIP1 = "10.50.10.31"
                prodIP2 = "10.50.10.32"
                devName1 = "ig201-dev"
                devName2 = "ig202-dev"
                prodName1 = "ig201"
                prodName2 = "ig202"
                break

            case "payment" : case "customer":
                devIP1 = "10.40.10.41"
                devIP2 = "10.40.10.42"
                prodIP1 = "10.50.10.41"
                prodIP2 = "10.50.10.42"
                devName1 = "ig301-dev"
                devName2 = "ig302-dev"
                prodName1 = "ig301"
                prodName2 = "ig302"
                break

            case "shipping":
                devIP1 = "10.40.10.51"
                devIP2 = "10.40.10.52"
                prodIP1 = "10.50.10.51"
                prodIP2 = "10.50.10.52"
                devName1 = "ig401-dev"
                devName2 = "ig402-dev"
                prodName1 = "ig401"
                prodName2 = "ig402"
                break
        }
    }

    String getSvrIP(String service, String whereto, boolean isProd)
    {
        setSvrIPs(service)
        if (whereto == "Alpha" )
        {
            if (isProd)
            {
                return prodIP1
            }else
            {
                return devIP1
            }

        }else if(whereto == "Beta")
        {
            if (isProd)
            {
                return prodIP2
            }else
            {
                return devIP2
            }

        }
    }

    String getSvrName(String service, String whereto, boolean isProd)
    {
        setSvrIPs(service)
        if (whereto == "Alpha" )
        {
            if (isProd)
            {
                return prodName1
            }else
            {
                return devName1
            }
        }else if (whereto == "Beta")
        {
            if (isProd)
            {
                return prodName2
            }else
            {
                return devName2
            }
        }
    }

}
