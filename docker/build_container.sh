#! /usr/bin/sh
echo build bigdata images
docker build --no-cache -t bigdata:$1 .

echo start master container ...
docker run -itd --name master --network spark-net --ip 172.20.0.2 -h master -p 18088:8088 -p 18888:8888 -p 51070:50070 --add-host worker1:172.20.0.3 --add-host worker1:172.20.0.4 --add-host mysql:172.20.0.5 bigdata:$1

echo start worker1 container...
docker run -itd --name worker1 --network spark-net --ip 172.20.0.3 -h worker1 --add-host master:172.20.0.2 --add-host worker2:172.20.0.4 --add-host mysql:172.20.0.5 bigdata:$1

echo start worker2 container...
docker run -itd --name worker2 --network spark-net --ip 172.20.0.4 -h worker2 --add-host master:172.20.0.2 --add-host worker1:172.20.0.3 --add-host mysql:172.20.0.5 bigdata:$1

echo start sshd...
docker exec -it master bash -c "/etc/init.d/ssh start"
docker exec -it worker1 bash -c "/etc/init.d/ssh start"
docker exec -it worker2 bash -c "/etc/init.d/ssh start"

# echo start service...
# docker exec -it master bash -c "sh ~/start-hadoop.sh"

echo finished
docker ps
