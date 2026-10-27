# Pagos — Microservicio de TicketWave

Microservicio REST para administrar pagos del caso TicketWave. La aplicación
usa Spring Boot, Java 25, Maven, Spring Data JPA y una base H2 en memoria.

## Tecnologías

| Componente | Tecnología |
|---|---|
| Aplicación | Spring Boot 3.5.0 |
| Lenguaje | Java 25 |
| Persistencia | Spring Data JPA + H2 |
| Documentación | springdoc OpenAPI, Swagger UI y ReDoc |
| Pruebas | JUnit, Mockito, MockMvc y Cucumber |
| Cobertura | JaCoCo con mínimo de 100% de líneas |
| Entrega | Docker, Docker Compose y GitHub Actions |

## Ejecución local

Requisitos: JDK 25, Maven 3.9 o superior y, para contenedores, Docker Compose.

```bash
mvn spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080`. El puerto puede
cambiarse con `PORT`.

## API

| Método | Ruta | Resultado |
|---|---|---|
| GET | `/api/pagos` | Lista pagos |
| GET | `/api/pagos/{id}` | Obtiene un pago o responde 404 |
| POST | `/api/pagos` | Crea un pago y responde 201 |
| PUT | `/api/pagos/{id}` | Actualiza un pago |
| DELETE | `/api/pagos/{id}` | Elimina un pago y responde 204 |

También están disponibles:

- Página de presentación: `/`
- Swagger UI: `/swagger-ui/index.html`
- OpenAPI YAML: `/v3/api-docs.yaml`
- ReDoc: `/redoc.html`
- Consola H2: `/h2-console`

## Evidencias de la EP2

### IE1 — Contenedores

El [Dockerfile](Dockerfile) utiliza dos etapas: Maven para compilar y una
imagen JRE separada para ejecutar. La imagen final:

- No incluye el código fuente ni las dependencias de Maven.
- Ejecuta con el usuario no privilegiado `app`.
- Expone un `HEALTHCHECK` contra la página raíz.
- Usa `.dockerignore` para no enviar artefactos, documentación ni metadatos de
  Git al contexto de construcción.

Construcción y ejecución local:

```bash
docker build -t duoc/casoejemplo-pagos:local .
docker run --rm -p 8080:8080 duoc/casoejemplo-pagos:local
```

### IE2 — Pruebas automatizadas

El job `test` ejecuta `mvn verify`, por lo que corre las pruebas unitarias,
MockMvc, Cucumber y la regla de cobertura JaCoCo. El pipeline se detiene si una
prueba falla o si la cobertura queda bajo el 100% configurado en Maven.

```bash
mvn test
mvn verify
```

Los reportes se publican como artefacto de GitHub Actions, incluso cuando una
prueba falla, para facilitar la revisión.

### IE3 — Seguridad y escalabilidad

- `.github/dependabot.yml` revisa semanalmente las dependencias Maven y las
  acciones de GitHub.
- El job `security` ejecuta Snyk con `--severity-threshold=high`. Un hallazgo
  alto o crítico hace fallar el job y bloquea los jobs siguientes.
- El secreto requerido es `SNYK_TOKEN`, configurado en los secretos del
  repositorio.
- Compose define dos réplicas como referencia de escalabilidad, límites de
  CPU/memoria y reinicio automático.

La ejecución de Snyk no usa credenciales en el código. Las actualizaciones de
Dependabot deben revisarse antes de fusionarse.

### IE4 — Despliegue y trazabilidad

El workflow [pipeline.yml](.github/workflows/pipeline.yml) ejecuta los jobs en
este orden:

```text
test + security -> build -> deploy
```

La imagen se etiqueta con `${GITHUB_SHA}`, de modo que cada ejecución queda
vinculada al commit que la originó. El job `deploy` levanta Docker Compose en el
runner, espera la disponibilidad del servicio, verifica `http://localhost:8080/`
y finalmente apaga el entorno simulado.

### IE5 — Orquestación

El [docker-compose.yml](docker-compose.yml) define:

- Healthcheck del servicio.
- Reinicio automático con `unless-stopped`.
- Dos réplicas declaradas y límites de CPU/memoria.
- Tag configurable mediante `IMAGE_TAG`.
- Puerto configurable mediante `PORT`.

```bash
IMAGE_TAG=local PORT=8080 docker compose up --build
docker compose down
```

Para demostrar dos réplicas reales detrás del mismo puerto, usa Docker Swarm:

```bash
docker swarm init
docker stack deploy --compose-file docker-compose.yml pagos
docker service ls
docker service ps pagos_pagos-svc
curl --fail http://localhost:8080/
docker stack rm pagos
```

El workflow realiza estos mismos pasos automáticamente. `deploy.replicas`,
`resources`, `restart_policy` y `update_config` se aplican al desplegar como
stack. En Compose local se mantiene una instancia para evitar conflictos al
publicar el puerto 8080.

## Documentación adicional

- [Resumen](docs/00_Resumen.md)
- [Arquitectura](docs/01_Arquitectura.md)
- [API](docs/02_API.md)
- [Pruebas](docs/03_Pruebas.md)
- [Despliegue](docs/04_Despliegue.md)

## Uso de inteligencia artificial

Se utilizó un asistente de IA como apoyo para revisar los requisitos de la EP2,
proponer la estructura del pipeline, detectar brechas en Docker/Compose y
mejorar la documentación. El equipo debe revisar y validar cada cambio antes
de entregarlo. Las reflexiones individuales y las conclusiones personales
deben ser redactadas por cada integrante sin apoyo de IA.

## Entrega y revisión

El equipo debe trabajar mediante ramas y Pull Requests, revisar el resultado
del pipeline en la pestaña Actions y entregar el enlace del repositorio según
las instrucciones de la asignatura.
