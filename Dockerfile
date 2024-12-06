FROM ibm-semeru-runtimes:open-21-jdk
RUN mkdir -p /app/_cache
COPY build/libs/openj9-howto-all.jar /app/app.jar
VOLUME /app/_cache
EXPOSE 8080
CMD ["java", "-Xvirtualized", "-Xshareclasses", "-Xshareclasses:name=sum", "-Xshareclasses:cacheDir=/app/_cache", "-jar", "/app/app.jar"]
