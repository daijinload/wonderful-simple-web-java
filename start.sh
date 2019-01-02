#!/bin/sh -ex

./gradlew clean
./gradlew build
./gradlew war
