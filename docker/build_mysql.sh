#! /usr/bin/sh
docker run --restart=always --network spark-net --ip 172.20.0.5 --name=mysql --privileged=true \
    -p 13306:3306 \
    -h mysql \
    -v $(pwd)/mysql/data:/var/lib/mysql \
    -e MYSQL_ROOT_PASSWORD=123456 \
    -d mysql:8.0.29
