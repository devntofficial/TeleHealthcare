FROM openjdk:14-jdk-alpine
ADD ./target/doctorService.jar /home/ubuntu/bmcappimages/doctorService.jar
WORKDIR /home/ubuntu/bmcappimages
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/home/ubuntu/bmcappimages/doctorService.jar"]