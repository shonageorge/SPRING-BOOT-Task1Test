FROM openjdk:11-jdk-stretch
ADD /target/muzix-0.0.1-SNAPSHOT.jar src/app/music/muzix-0.0.1-SNAPSHOT.jar
WORKDIR /src/app/music
ENTRYPOINT ["java","-jar","muzix-0.0.1-SNAPSHOT.jar"]

