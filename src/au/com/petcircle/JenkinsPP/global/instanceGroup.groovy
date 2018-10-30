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
                devName1 = "yellow01-dev"
                devName2 = "yellow02-dev"
                prodName1 = "yellow01"
                prodName2 = "yellow02"
                break

            case "merch": case "order": case "search":
                devIP1 = "10.40.10.31"
                devIP2 = "10.40.10.32"
                prodIP1 = "10.50.10.31"
                prodIP2 = "10.50.10.32"
                devName1 = "black01-dev"
                devName2 = "black02-dev"
                prodName1 = "black01"
                prodName2 = "black02"
                break

            case "payment" : case "customer": case "auth":
                devIP1 = "10.40.10.41"
                devIP2 = "10.40.10.42"
                prodIP1 = "10.50.10.41"
                prodIP2 = "10.50.10.42"
                devName1 = "red01-dev"
                devName2 = "red02-dev"
                prodName1 = "red01"
                prodName2 = "red02"
                break

            case "shipping":
                devIP1 = "10.40.10.51"
                devIP2 = "10.40.10.52"
                prodIP1 = "10.50.10.51"
                prodIP2 = "10.50.10.52"
                devName1 = "chocolate01-dev"
                devName2 = "chocolate02-dev"
                prodName1 = "chocolate01"
                prodName2 = "chocolate02"
                break

            case "shiba":
                devIP1 = "10.40.0.11"
                devIP2 = "10.40.0.12"
                prodIP1 = "10.50.0.11"
                prodIP2 = "10.50.0.12"
                devName1 = "shiba01-dev"
                devName2 = "shiba02-dev"
                prodName1 = "shiba01"
                prodName2 = "shiba02"
                break

        }
    }

    String getSvrIP(String service, String whereto, boolean isProd)
    {
        setSvrIPs(service)
        if (whereto == "01" )
        {
            if (isProd)
            {
                return prodIP1
            }else
            {
                return devIP1
            }

        }else if(whereto == "02")
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

        if (service == "shiba")
        {
            if (whereto == "01" )
            {
                if (isProd)
                {
                    return prodName1
                }else
                {
                    return devName1
                }
            }else if (whereto == "02")
            {
                if (isProd)
                {
                    return prodName2
                }else
                {
                    return devName2
                }
            }

        }else{
            if (whereto == "01" )
            {
                if (isProd)
                {
                    return "labrador-"+prodName1
                }else
                {
                    return "labrador-"+devName1
                }
            }else if (whereto == "02")
            {
                if (isProd)
                {
                    return "labrador-"+prodName2
                }else
                {
                    return "labrador-"+devName2
                }
            }
        }
    }

}
