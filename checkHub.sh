#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER
# MODULE

echo "Checking if hub is ready - $HUB_HOST"

while [ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done

# start the java command
java -cp lennoxpros-0.0.1-SNAPSHOT.jar:lennoxpros-0.0.1-SNAPSHOT.jar.original:lennoxpros-0.0.1-SNAPSHOT-tests.jar:libs/* -Dspring.profiles.active=$PROFILE -Dhub_host=$HUB_HOST -Dbrowser=$BROWSER org.testng.TestNG $MODULE