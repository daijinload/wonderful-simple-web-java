#!/bin/sh -ex

pkill -f -9 'com.gmail.daijinload' &
pkill -f -9 'java -jar wonderful-simple-web-java-0.1.war' &

./gradlew clean
./gradlew build
./gradlew war
