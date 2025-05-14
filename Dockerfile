FROM ubuntu/jre:21-24.04_edge


#Create
WORKDIR /app


#Copy packkage
COPY target/urlShortenerChartPlus-0.0.1-SNAPSHOT.jar app.jar


#Run file
ENTRYPOINT ["java","-jar","app.jar"]