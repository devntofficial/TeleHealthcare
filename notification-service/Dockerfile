FROM openjdk:14-jdk-alpine
ADD ./target/notificationService.jar /home/ubuntu/bmcappimages/notificationService.jar
WORKDIR /home/ubuntu/bmcappimages
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/home/ubuntu/bmcappimages/notificationService.jar"]