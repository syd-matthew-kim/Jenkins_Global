#!/usr/bin/env  groovy
import groovy.json.JsonSlurper

print(checkservices("product"))

def checkservices(service){
    def url = "http://10.30.0.3:8081/service/rest/beta/search?maven.groupId=au.com.petcircle&maven.artifactId="+service+"&maven.extension=war"
    print(url)
    def xml = url.toURL().text

    JsonSlurper parser = new groovy.json.JsonSlurper()
    Map parsedJson = parser.parseText(xml)
    return parsedJson.items.version.reverse()
}


#!/usr/bin/env  groovy
import groovy.json.JsonSlurper

def url = "http://10.30.0.3:8081/service/rest/beta/search?maven.groupId=au.com.petcircle&maven.artifactId="+service+"&maven.extension=war"
def xml = url.toURL().text
JsonSlurper parser = new groovy.json.JsonSlurper()
Map parsedJson = parser.parseText(xml)
return parsedJson.items.version.reverse()
