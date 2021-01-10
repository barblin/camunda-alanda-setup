#!/bin/bash
# Startup script

read -p "Enter JAVA_HOME [C://Program\ Files//Java//jdk-11]: " java_input
java=${java_input:-C://Program\ Files//Java//jdk-11}
echo $java

export JAVA_HOME=$java
export PATH=$JAVA_HOME/bin:$PATH

printf "\n"
echo "Install camunda services..."
cd ./com.alanda.camunda.services 
./mvnw -f pom.xml clean install -U

printf "\n"
echo "Install alanda process service..."
cd ..
cd ./com.alanda.process.services 
./mvnw -f pom.xml clean install -U

printf "\n"
echo "Creating environment with docker..."
echo "Using development configuration ./config/.env.dev"
cd ..

rm ./logs/startup.log
docker-compose --env-file ./config/.env.dev up --build --force-recreate > ./logs/startup.log 2>&1 & 

echo "Please wait for the docker setup to be complete..."

sleep 10

printf "\n"

read -p "Upload initial file ./ressources/payment.bpmn (Any key): " key
./scripts/upload-bpmn.sh

printf "\n"
echo "##############"
echo "Setup complete"
echo "##############"