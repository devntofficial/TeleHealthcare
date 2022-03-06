FROM openjdk:14-jdk-alpine
ADD ./target/userService.jar /home/ubuntu/bmcappimages/userService.jar
WORKDIR /home/ubuntu/bmcappimages
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/home/ubuntu/bmcappimages/userService.jar"]