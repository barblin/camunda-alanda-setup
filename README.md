# camunda-alanda-setup

Make sure that you have execution rights for all folders and subfolders - to make sure, execute following command

Pls install openjdk with Java version 11 and above


All the necessary components are contained within this project. 

Before you start, please make sure that docker with docker compose is setup on your unix machine.

Currently used docker compose version:
```
version: "3.8"
``` 
For more details, please refer to the docker-compose.yml file


To execute the setup, simply type:
```
docker-compose up
```

This will download and setup a postgres database.
For the purposes of this demonstration, it will be accessible via 
```
http://localhost:5432
```

