#!/bin/bash
# A simple script

docker-compose --env-file ./config/.env.dev down
rm -r ./com.alanda.camunda.services/target
rm -r ./com.alanda.process.services/target