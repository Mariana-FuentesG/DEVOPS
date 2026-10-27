cd "C:\Users\benja\OneDrive\Documentos\DevOps\Ev2\DEVOPS"

mvn verify

docker compose config

docker build -t duoc/casoejemplo-pagos:local .

docker run -d --name pagos-prueba -p 8080:8080 duoc/casoejemplo-pagos:local

Invoke-WebRequest http://localhost:8080/

docker inspect --format "{{.Config.User}}" pagos-prueba

docker inspect --format "{{.State.Health.Status}}" pagos-prueba

docker stop pagos-prueba

docker rm pagos-prueba

# Demostracion IE5: dos replicas reales con Docker Swarm
docker swarm init
docker stack deploy --compose-file docker-compose.yml pagos
docker service ls
docker service ps pagos_pagos-svc
Invoke-WebRequest http://localhost:8080/
docker stack rm pagos