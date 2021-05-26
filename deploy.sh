#!/bin/bash

echo "> Pull"
git pull

echo "> Build"
./gradlew build --exclude-task test
# 잠시 테스트 중단

echo "CHECK PID.."
PID=$(pgrep -fl fick | awk '{print $1}')

if [ -z "$PID" ]; then
  echo "PID error"
else
  kill -15 $PID
  sleep 5
  echo "application terminated"
fi

JAR_NAME=$(ls -tr build/libs | tail -n 1)
echo "deploy $JAR_NAME"

nohup java -jar build/libs/$JAR_NAME 2>&1 &

