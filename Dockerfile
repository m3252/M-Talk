FROM openjdk:11
ADD target/members-mysql.jar members-mysql.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "members-mysql.jar"]