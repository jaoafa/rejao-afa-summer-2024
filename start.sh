#!/bin/bash

# build rejao-afa
cd ./rejao-afa || exit
docker compose build
docker compose up

cd ..
mv ./rejao-afa/build/libs/rejao-afa-1.0-SNAPSHOT-all.jar ./plugins/rejao-afa-1.0-SNAPSHOT-all.jar
docker compose build
docker compose up -d