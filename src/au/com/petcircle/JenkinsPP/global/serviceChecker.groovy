#!/usr/bin/env groovy

package au.com.petcircle.JenkinsPP.global

class serviceChecker {
    serviceValues v = new serviceValues();

    serviceValues getValues(String serviceName){
        switch (serviceName){
            case "shiba" :
                v.setMin(10);
                v.setMax(19);
                v.setBaseName("shiba00");
                v.setBaseIp("10.50.0.10");
                break;
            case "minpin" :
                v.setMin(10);
                v.setMax(19);
                v.setBaseName("minpin00");
                v.setBaseIp("10.50.10.10");
                break;
            case "product" :
                v.setMin(20);
                v.setMax(29);
                v.setBaseName("labrador-product00");
                v.setBaseIp("10.50.10.20");
                break;
            case "payment" :
                v.setMin(30);
                v.setMax(39);
                v.setBaseName("labrador-payment00");
                v.setBaseIp("10.50.10.30");
                break;
            case "customer":
                v.setMin(40);
                v.setMax(49);
                v.setBaseName("labrador-customer00");
                v.setBaseIp("10.50.10.40");
                break;
            case "order":
                v.setMin(50);
                v.setMax(59);
                v.setBaseName("labrador-order00");
                v.setBaseIp("10.50.10.50");
                break;
            case "merch":
                v.setMin(60);
                v.setMax(69);
                v.setBaseName("labrador-merch00");
                v.setBaseIp("10.50.10.60");
                break;
            case "shipping":
                v.setMin(70);
                v.setMax(79);
                v.setBaseName("labrador-shipping00");
                v.setBaseIp("10.50.10.70");
                break;
            case "search":
                v.setMin(80);
                v.setMax(89);
                v.setBaseName("labrador-search00");
                v.setBaseIp("10.50.10.80");
                break;
            case "review":
                v.setMin(90);
                v.setMax(99);
                v.setBaseName("labrador-review00");
                v.setBaseIp("10.50.10.90");
                break;
        }
        return v;
    }

    int getMin(String serviceName) {
        v = this.getValues(serviceName);
        return v.getMin();
    }
    int getMax(String serviceName) {
        v = this.getValues(serviceName);
        return v.getMax();
    }
    String getBaseName(String serviceName) {
        v = this.getValues(serviceName);
        return v.getBaseName();
    }
    String getBaseIp(String serviceName) {
        v = this.getValues(serviceName);
        return v.getBaseIp();
    }

}
