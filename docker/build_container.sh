#! /usr/bin/sh
echo build bigdata images
docker build --no-cache -t bigdata:$1 .

echo start master container ...
docker run -itd --name master --network spark-net --ip 172.20.0.2 -h master --privileged -p 18032:8032 -p 28080:18080 -p 29888:19888 -p 17077:7077 -p 51070:50070 -p 18888:8888 -p 19000:9000 -p 11100:11000 -p 51030:50030 -p 18050:8050 -p 18081:8081 -p 18900:8900 -p 18088:8088 --add-host worker1:172.20.0.3 --add-host worker1:172.20.0.4 --add-host mysql:172.20.0.5 bigdata:$1

echo start worker1 container...
docker run -itd --name worker1 --network spark-net --ip 172.20.0.3 -h worker1 --privileged -p 18042:8042 -p 51010:50010 -p 51020:50020 --add-host master:172.20.0.2 --add-host worker2:172.20.0.4 --add-host mysql:172.20.0.5 bigdata:$1

echo start worker2 container...
docker run -itd --name worker2 --network spark-net --ip 172.20.0.4 -h worker2 --privileged -p 18043:8042 -p 51011:50011 -p 51021:50021 --add-host master:172.20.0.2 --add-host worker1:172.20.0.3 --add-host mysql:172.20.0.5 bigdata:$1

echo start sshd...
docker exec -it master bash -c "/etc/init.d/ssh start"
docker exec -it worker1 bash -c "/etc/init.d/ssh start"
docker exec -it worker2 bash -c "/etc/init.d/ssh start"

# echo start service...
# docker exec -it master bash -c "bash ~/start-hadoop.sh"

echo finished
docker ps
