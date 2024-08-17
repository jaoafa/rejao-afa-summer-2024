# build rejao-afa
Set-Location ./rejao-afa
docker compose build
docker compose up

Set-Location ..
Move-Item "./rejao-afa/build/libs/rejao-afa-1.0-SNAPSHOT-all.jar" "./plugins/rejao-afa-1.0-SNAPSHOT-all.jar" -Force
docker compose build
docker compose up -d