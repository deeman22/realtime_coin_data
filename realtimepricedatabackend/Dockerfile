FROM azul/zulu-openjdk:17-latest
VOLUME /tmp
COPY target/*.war realtimepricedatabackend.war
ENTRYPOINT ["java","-jar","/realtimepricedatabackend.war"]
