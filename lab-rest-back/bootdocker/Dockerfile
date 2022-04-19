# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM adoptopenjdk/openjdk8:latest
#ADD target/persona.jar persona.jar
RUN mkdir /usr/src/bootdocker
COPY persona-0.0.1-SNAPSHOT.jar /usr/src/bootdocker/persona-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/bootdocker
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/usr/src/bootdocker/persona-0.0.1-SNAPSHOT.jar"]


