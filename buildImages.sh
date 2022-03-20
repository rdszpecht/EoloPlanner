username=${1:-rsotosan}

#Compila topoService y lo sube directamente
mvn compile -f toposervice/pom.xml jib:build -Dimage=${username}/toposervice:1.0

#Compila planner y pushea a DockerHub
docker build -t ${username}/eoloplanner:1.0 -f planner/multistage.Dockerfile planner
docker push ${username}/eoloplanner:1.0

#Compila server y lo pushea a DockerHub
docker build -t ${username}/server:1.0 server/
docker push ${username}/server:1.0

#Compila weatherService y lo pushea a DockerHub
pack build ${username}/weatherservice:1.0 --path weatherservice/ --builder docker.io/paketobuildpacks/builder:base
docker push ${username}/weatherservice:1.0