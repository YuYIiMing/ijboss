FROM openjdk:8-jdk-alpine
#FROM nimmis/java-centos:oracle-8-jdk
VOLUME /tmp
ARG JAR_FILE=service/target/app.jar

#COPY starter.sh /starter.sh
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
#ENTRYPOINT ["sh","/starter.sh"]

#COPY ${JAR_FILE} /app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${app_profiles_active} -Dlog.accessKeyId=${log_accesskeyid} -Dlog.accessKey=${log_accesskey}  -Dlog.project=${log_project}","-jar","/app.jar"]

COPY starter.sh /starter.sh
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["sh","/starter.sh"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${app_profiles_active} -Dlog.accessKeyId=${log_accesskeyid} -Dlog.accessKey=${log_accesskey}  -Dlog.project=${log_project}","-jar","/app.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+8","-Dspring.profiles.active=${app_profiles_active}","-Dlog.accessKeyId=${log_accesskeyid}","-Dlog.accessKey=${log_accesskey}","-Dlog.project=${log_project}","-jar","/app.jar"]