Set-Location ./rejao-afa
./gradlew.bat build
Set-Location ..
Move-Item "./rejao-afa/build/libs/rejao-afa-1.0-SNAPSHOT-all.jar" "./plugins/rejao-afa-1.0-SNAPSHOT-all.jar" -Force
docker compose up -d