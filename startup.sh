#!/bin/bash
# A simple script

read -p "Enter JAVA_HOME [C://Program\ Files//Java//jdk-11]: " java_input
java=${java_input:-C://Program\ Files//Java//jdk-11}
echo $java

export JAVA_HOME=$java
export PATH=$JAVA_HOME/bin:$PATH

cd ./com.alanda.camunda.services 
./mvnw -f pom.xml clean install -U

cd ..
cd ./com.alanda.process.services 
./mvnw -f pom.xml clean install -U

cd ..
docker-compose up --build --force-recreate