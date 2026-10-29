# Pagos — Despliegue y operación

## Requisitos

- Java 25, Maven 3.9 (build local)
- Docker + Docker Compose (despliegue)

## Ejecución local

```bash
mvn spring-boot:run
```

URLs en `http://localhost:8080`:

| Recurso | URL |
|---------|-----|
| Página de presentación | `/` |
| Swagger UI | `/swagger-ui/index.html` |
| OpenAPI yaml | `/v3/api-docs.yaml` |
| ReDoc | `/redoc.html` |
| H2 Console | `/h2-console` |
| API base | `/api/pagos` |

## Docker

```bash
docker build -t duoc/casoEjemplo-pagos:local .
docker run -p 8080:8080 duoc/casoEjemplo-pagos:local
```

La imagen se construye en dos etapas y la aplicación se ejecuta con el usuario
no privilegiado `app`. El healthcheck consulta la página raíz del servicio.

## Docker Compose

```bash
docker compose up --build
PORT=9090 docker compose up --build   # cambiar puerto host
```

Compose declara dos réplicas de referencia, límites de CPU y memoria, reinicio
automático y healthcheck. Para una ejecución local simple:

```bash
IMAGE_TAG=local docker compose up --build
```

Para demostrar dos réplicas reales con balanceo en el puerto publicado, usa
Docker Swarm:

```bash
docker swarm init
docker stack deploy --compose-file docker-compose.yml pagos
docker service ls
docker service ps pagos_pagos-svc
curl --fail http://localhost:8080/
docker stack rm pagos
```

La evidencia esperada en `docker service ls` es `2/2` en la columna `REPLICAS`.

## Pipeline CI/CD

`.github/workflows/pipeline.yml` ejecuta cuatro etapas:

1. `test`: corre `mvn verify`, incluyendo cobertura.
2. `security`: ejecuta Snyk y bloquea hallazgos de severidad alta o crítica.
3. `build`: construye la imagen con una etiqueta igual al SHA del commit.
4. `deploy`: construye la imagen trazable, inicializa Docker Swarm, despliega
   Compose como stack con dos réplicas, verifica el endpoint y elimina el
   entorno simulado.

El job de deploy solo comienza cuando pruebas, seguridad y construcción
terminaron correctamente. El token de Snyk se entrega mediante el secreto
`SNYK_TOKEN` y nunca se guarda en el repositorio.

## Base de datos

H2 en memoria (se pierde al detener el contenedor), configurable en `src/main/resources/application.yml`. Consola web en `/h2-console` (JDBC URL, usuario `sa`, sin clave).

## Variables de entorno

| Variable | Defecto | Descripción |
|----------|---------|-------------|
| `PORT` | `8080` | Puerto del servidor y mapeo del contenedor |
