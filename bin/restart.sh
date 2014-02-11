PID=`ps -eo pid,cmd | grep 'java.*\-jar.*kune.*jar' | grep -v 'grep' | sed  's/^\ *//' | cut -d\  -f 1`

if [ -n "$PID" ]
then
  kill $PID
  sleep 1
fi

DIR=`dirname $0`
$DIR/server.sh -j $DIR/../target/kune-0.3.0-SNAPSHOT-complete.jar -c -a
