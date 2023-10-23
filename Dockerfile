FROM openjdk:17
COPY ./build/libs/keywordService.jar keywordService.jar
ENTRYPOINT ["java", "-jar", "keywordService.jar"]