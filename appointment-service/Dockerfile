FROM openjdk:14-jdk-alpine
ADD ./target/appointmentService.jar /home/ubuntu/bmcappimages/appointmentService.jar
WORKDIR /home/ubuntu/bmcappimages
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/home/ubuntu/bmcappimages/appointmentService.jar"]