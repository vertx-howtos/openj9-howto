FROM adoptopenjdk/openjdk12-openj9:alpine-slim
RUN mkdir -p /app/_cache
COPY build/libs/openj9-howto-all.jar /app/app.jar
VOLUME /app/_cache
EXPOSE 8080
CMD ["java", "-Xvirtualized", "-Xshareclasses", "-Xshareclasses:name=sum", "-Xshareclasses:cacheDir=/app/_cache", "-jar", "/app/app.jar"]
