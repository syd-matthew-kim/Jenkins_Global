#!/usr/bin/env groovy

package au.com.petcircle.JenkinsPP.global

class serviceValues {
    int min;
    int max;
    String baseName;
    String baseIp;

    int getMin() {
        return min
    }

    void setMin(int min) {
        this.min = min
    }

    int getMax() {
        return max
    }

    void setMax(int max) {
        this.max = max
    }

    String getBaseName() {
        return baseName
    }

    void setBaseName(String baseName) {
        this.baseName = baseName
    }

    String getBaseIp() {
        return baseIp
    }

    void setBaseIp(String baseIp) {
        this.baseIp = baseIp
    }

}
