#!/bin/bash

mvn package
docker-compose build
docker-compose up