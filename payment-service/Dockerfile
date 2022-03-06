FROM openjdk:14-jdk-alpine
ADD ./target/paymentService.jar /home/ubuntu/bmcappimages/paymentService.jar
WORKDIR /home/ubuntu/bmcappimages
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "/home/ubuntu/bmcappimages/paymentService.jar"]