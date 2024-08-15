#!/bin/bash

cd ./rejao-afa || exit
./gradlew build
cd ..
mv ./rejao-afa/build/libs/rejao-afa-1.0-SNAPSHOT-all.jar ./plugins/rejao-afa-1.0-SNAPSHOT-all.jar
docker compose up -d