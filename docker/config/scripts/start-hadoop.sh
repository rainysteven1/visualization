#!/bin/bash
echo -e "\n"

hdfs namenode -format -force

echo -e "\n"

$HADOOP_HOME/sbin/start-dfs.sh

echo -e "\n"

$HADOOP_HOME/sbin/start-yarn.sh

echo -e "\n"

$SPARK_HOME/sbin/start-all.sh

echo -e "\n"

jps

echo -e "\n"

hadoop dfsadmin -safemode leave

echo -e "\n"

hdfs dfs -mkdir /mr-history

hdfs dfs -mkdir /stage

echo -e "\n":
