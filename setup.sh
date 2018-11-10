#!/bin/sh -ex

sudo groupadd -r jetty
sudo useradd -r -g jetty jetty

export JETTY_VERSION='9.4.12.v20180830'
export JETTY_TGZ_URL="https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-home/$JETTY_VERSION/jetty-home-$JETTY_VERSION.tar.gz"
export JETTY_TGC_URL="https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-home/$JETTY_VERSION/jetty-home-$JETTY_VERSION.tar.gz.sha1"
export JETTY_TGZ_NAME='jetty.tar.gz'

export JETTY_HOME=/usr/local/jetty
export JETTY_BASE=/var/lib/jetty
export PATH="$JETTY_HOME/bin":"$PATH"

# create jetty home
sudo mkdir -p "$JETTY_HOME"
cd $JETTY_HOME
sudo curl -SL "$JETTY_TGZ_URL" -o $JETTY_TGZ_NAME
echo "`curl -SL "$JETTY_TGC_URL"` $JETTY_TGZ_NAME" | sha1sum -c -
sudo tar -xvf jetty.tar.gz --strip-components=1
sudo sed -i '/jetty-logging/d' etc/jetty.conf
sudo rm jetty.tar.gz*

# create jetty base
sudo mkdir -p "$JETTY_BASE"
sudo java -jar "$JETTY_HOME/start.jar" --create-startd --add-to-start="server,http,deploy,jsp,jstl,ext,resources,websocket"

sudo chown -R jetty:jetty "$JETTY_BASE"
sudo chown -R jetty:jetty "$JETTY_HOME"

sudo rm -rf /tmp/hsperfdata_root
