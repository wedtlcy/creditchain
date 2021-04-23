#!/bin/sh

export JRE_HOME=$JAVA_HOME/jre
#当前目录
CUR_DIR=$(cd "$(dirname "$0")"; pwd)

## service name
SERVER_NAME=creditlink-center-job

SERVICE_DIR=$CUR_DIR
JAR_NAME=$SERVER_NAME\.jar

cd $SERVICE_DIR

case "$1" in

    start)
        nohup $JRE_HOME/bin/java -Xms256m -Xmx512m -jar $JAR_NAME --server.port=9504 >/dev/null 2>&1 &
        echo "=== start $SERVER_NAME"
        ;;

    stop)
        PID=$(ps -ef | grep $JAR_NAME | grep -v grep | awk '{ print $2 }')
	if [ -z "$PID" ]
	then
	echo Application is already stopped
	else
	echo kill $PID
	kill $PID
	fi
	;;

    restart)
        $0 stop
        sleep 2
        $0 start
        echo "=== restart $SERVER_NAME"
        ;;

    *)
        ## restart
        $0 stop
        sleep 2
        $0 start
        ;;

esac
exit 0
