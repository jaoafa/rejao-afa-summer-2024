cd ./rejao-afa
./gradlew.bat build
cd ..
Move-Item "./rejao-afa/build/libs/rejao-afa-1.0-SNAPSHOT-all.jar" "./plugins/rejao-afa-1.0-SNAPSHOT-all.jar"
docker compose up -d