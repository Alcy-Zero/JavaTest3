
mvn clean package

docker build -t mysql:test ./docker/mysql/
docker build -t jar:test .

docker-compose up


