#!/bin/bash

# install vivecraft

VIVECRAFT_RELEASE=120r1
VIVECRAFT_VERSION=1.20.6r1

VIVECRAFT_FILENAME="Vivecraft-${VIVECRAFT_RELEASE}-${VIVECRAFT_VERSION}"

if [ ! -f "/plugins/${VIVECRAFT_FILENAME}.jar" ]; then
  echo "Installing Vivecraft ${VIVECRAFT_VERSION} for Spigot ${VIVECRAFT_RELEASE}"

  rm -fv "/plugins/Vivecraft-*.jar"

  curl -fsSL -o "/tmp/${VIVECRAFT_FILENAME}.zip" "https://github.com/jrbudda/Vivecraft_Spigot_Extensions/releases/download/${VIVECRAFT_RELEASE}/Vivecraft_Spigot_Extensions.${VIVECRAFT_VERSION}.zip"

  unzip -o "/tmp/${VIVECRAFT_FILENAME}.zip" -d "/plugins"
  rm -fv "/tmp/${VIVECRAFT_FILENAME}.zip"

  mv -v /plugins/Vivecraft_Spigot_Extensions.jar "/plugins/${VIVECRAFT_FILENAME}.jar"
fi

cd /whitelist-me-receiver || exit
nohup pnpm start &

cd /

# run
/start
