docker stop master
docker stop worker1
docker stop worker2
echo stop containers

docker rm master
docker rm worker1
docker rm worker2
echo rm containers

docker ps
