#!/bin/bash
#set -x

src_dir=/Users/wenhao/IdeaProjects/spring-cloud-test
echo $src_dir
cd $src_dir

miro_start(){

	for i in `find ${src_dir} -name "*SNAPSHOT.jar" | cut -d "/" -f 6`
	do
		 #nohup java -server -Xmx256m -Xms256m -Xmn128m -XX:PermSize=128m -Xss256k -jar `find ${src_dir}/$i/target -name "*-SNAPSHOT.jar"`  > "nohup_${i}.out" &
		 nohup java -server -Xmx256m -Xms256m -Xmn128m -XX:PermSize=128m -Xss256k -jar `find ${src_dir}/$i/target -name "*-SNAPSHOT.jar"` &
	done
}

miro_end(){

	for i in `ps -ef | grep microserver | grep -v grep | awk '{print $2}'`
	do
		 kill ${i}
	done
}

if [ $# = 1 -a $1 = "start" ]; then
  echo "Begin to start the miro ..."
  miro_start
elif [ $# = 1 -a $1 = "stop" ]; then
  echo "Begin to shutdown dubbo ..."
  miro_end
else
  echo "Your param is wrong!"
  echo "usage:"
  echo "$0 [ start | stop | restart | status ]"
  echo "The default param: log"
  exit 1
fi