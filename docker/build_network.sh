#! /usr/bin/sh
echo create network
docker network create --driver=bridge --subnet=172.20.0.0/16 spark-net
echo create success
docker network ls
