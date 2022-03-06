FROM openjdk:14-jdk-alpine
ADD ./target/ratingService.jar /home/ubuntu/bmcappimages/ratingService.jar
WORKDIR /home/ubuntu/bmcappimages
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/home/ubuntu/bmcappimages/ratingService.jar"]