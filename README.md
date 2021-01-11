# camunda-alanda-setup

To setup the application, you simply have to fulfill the prerequisites and execute the shell script:

```
./startup.sh
```

## Prerequisites

This setup was create for an Ubuntu Linux environment and tested with the current LTS version 20.04.1. Make sure that
you have execution rights for all folders and subfolders

### Java Home

Before you attempt to start the **startup.sh** script. Please make sure to have the openjdk **version 11** installed.
You can install it, by simply executing this command:

```
apt-get install openjdk-11-jdk
```

While startup.sh execution, you will be asked to provide the path to the openjdk or just use the default location:

```
/usr/lib/jvm/java-11-openjdk-amd64
```

This will be the default installation directory if you install openjdk-11-jdk. Please verify that this is also correct
for you.

### Docker compose

During the startup, we will create docker container and therefore you also have to make sure that docker-compose is
installed. You can do that with this command:

```
apt-get install docker-compose
```

### Execution rights

Make sure that you have execute permissions. To make sure, you can simply execute 

```chmod -R 755 ./``

## Steps of startup.sh

* install curl
* export java home
* install the project:
  `
  com.alanda.camunda.services
  `
* install the project:
  `
  com.alanda.process.services
  `
* create docker containers for the application via docker compose and specified by `docker-compose.yml`
* performing health checks until application responds with 200 or 30 attempts have been made. Call to
  `
  ./scripts/health-check.sh
  `
* upload initial file with user input
  `
  ./scripts/upload-bpmn.sh
  `

## Directory structure

* `./com.alanda.camunda.services` contains a spring boot application that depends on the camunda library and connects to
  a Postgres DB which is set up by docker-compose.

* `./com.alanda.process.services` contains a spring boot application offering a REST API to upload .bpmn files to
  aforementioned service

* `./config` contains an environment file for development setups. This file is consumed by docker-compose

* `./documentation` contains the files providing additional insights into the functionality of the system:
    - `Camunda Alanda Setup.postman_collection.json` a postman collection with every supported REST call. For example to
      upload different .bpmn files or to start a process.
    - `LeadDev_Uebung_alanda.pdf` the initially provided requirements file.
    - `Protocol-Requirements.ods` a structured view of the requirements, with approximate times for implementation

* `./logs` contains the logs of the **docker-compose up** command. This command is executed in the background and writes
  the logs into this directory.

* `./ressources` contains the file which will be uploaded during initial setup. It is also the directory which will be 
served as a volume to the container **process-app**. If you want to provide additional files for uploads, 
  add them to this directory.

* `./scripts` contains helper scripts, executed by the setup. 

* `./docker-compose.yml` is a file that contains information about the docker containers that we want to create, 
  how they depend on each other, how they communicate and other details important to the creation of the containers.

* `./startup.sh` setup file for the system.

* `./shutdown.sh` will tear down the docker containers and remove the target folders of the two application directories.


## Supported endpoints

### Upload process file
```
POST http://localhost:8080/api/uploads/processes
``` 

### Start process
```
http://localhost:4000/engine-rest/process-definition/key/payment-retrieval/start
``` 

### Get amount of executions and timestamp
```
http://localhost:4000/api/processes/executions
```

## Camunda UI

To verify startup success and file-upload success, please visit
http://localhost:4000/camunda/app/cockpit/default/#/dashboard