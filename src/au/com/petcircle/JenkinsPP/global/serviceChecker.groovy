#!/usr/bin/env groovy

package au.com.petcircle.JenkinsPP.global

class serviceChecker {
    int min;
    int max;
    String baseName;
    String baseIp;

    void setValues(String serviceName){
        switch (serviceName){
            case "shiba" :
                min = 10;
                max = 19;
                baseName = "shiba00";
                baseIp = "10.50.0.10";
                break;
            case "minpin" :
                min = 10;
                max = 19;
                baseName = "minpin00";
                baseIp = "10.50.10.10";
                break;
            case "product" :
                min = 20;
                max = 29;
                baseName = "labrador-product00";
                baseIp = "10.50.10.20";
                break;
            case "payment" :
                min = 30;
                max = 39;
                baseName = "labrador-payment00";
                baseIp = "10.50.10.30";
                break;
            case "customer":
                min = 40;
                max = 49;
                baseName = "labrador-customer00";
                baseIp = "10.50.10.40";
                break;
            case "order":
                min = 50;
                max = 59;
                baseName = "labrador-order00";
                baseIp = "10.50.10.50";
                break;
            case "merch":
                min = 60;
                max = 69;
                baseName = "labrador-merch00";
                baseIp = "10.50.10.60";
                break;
            case "shipping":
                min = 70;
                max = 79;
                baseName = "labrador-shipping00";
                baseIp = "10.50.10.70";
                break;
            case "search":
                min = 80;
                max = 89;
                baseName = "labrador-search00";
                baseIp = "10.50.10.80";
                break;
            case "review":
                min = 90;
                max = 99;
                baseName = "labrador-review00";
                baseIp = "10.50.10.90";
                break;
        }
    }

    int getMin(String serviceName) {
        setValues(serviceName);
        return min;
    }
    int getMax(String serviceName) {
        setValues(serviceName);
        return max;
    }
    String getBaseName(String serviceName) {
        setValues(serviceName);
        return baseName;
    }
    String getBaseIp(String serviceName) {
        setValues(serviceName);
        return baseIp;
    }

}