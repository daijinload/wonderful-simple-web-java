#!/bin/sh -ex

pkill -f -9 'app.sample.Main'

./gradlew clean
./gradlew build
./gradlew war
