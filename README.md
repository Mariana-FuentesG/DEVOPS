# Pagos — Microservicio de riesgo pagos

Microservicio correspondiente al **caso casoEjemplo — TicketWave** (Venta y control de acceso de entradas para eventos en vivo) de la Evaluación Parcial N°1.

| | |
|---|---|
| Stack | Spring Boot 3.3 · Java 21 · Maven · Spring Data JPA · H2 · springdoc-openapi |
| Calidad | JaCoCo cobertura LINE 100% · Cucumber (BDD) alineado a endpoints REST |
| Entrega | Docker / Docker Compose |

## Responsabilidad (SRP)

administra los datos y la lógica del dominio de Pagos del caso casoEjemplo (TicketWave). Su base de datos es una **H2 en memoria** (un solo microservicio por base), cumpliendo aislamiento de datos por dominio.

## Página de presentación

Al ejecutar el servicio, `http://localhost:8080/` muestra la página de presentación del microservicio con documentación y enlaces a:

- **Swagger UI**: `/swagger-ui/index.html`
- **OpenAPI (yaml)**: `/v3/api-docs.yaml`
- **ReDoc**: `/redoc.html`
- **H2 Console**: `/h2-console`

## Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/pagos` | Lista todos los recursos |
| GET | `/api/pagos/{id}` | Obtiene un recurso por id |
| POST | `/api/pagos` | Crea un recurso |
| PUT | `/api/pagos/{id}` | Actualiza un recurso |
| DELETE | `/api/pagos/{id}` | Elimina un recurso |

## Documentación del proyecto

La documentación completa está en la carpeta [`docs/`](docs/):

- [`docs/00_Resumen.md`](docs/00_Resumen.md) — propósito, responsabilidad y tecnologías
- [`docs/01_Arquitectura.md`](docs/01_Arquitectura.md) — componentes, arquitectura y patrones
- [`docs/02_API.md`](docs/02_API.md) — contrato REST y ejemplos curl
- [`docs/03_Pruebas.md`](docs/03_Pruebas.md) — tests unitarios, cobertura y Cucumber
- [`docs/04_Despliegue.md`](docs/04_Despliegue.md) — Docker, Docker Compose e integración

## Cómo ejecutar locmente

```bash
mvn spring-boot:run
```

## Cómo ejecutar con Docker

```bash
docker compose up --build
# http://localhost:8080
```

## Cómo ejecutar las pruebas

```bash
mvn test      # unit tests + Cucumber
mvn verify    # + verificación de cobertura JaCoCo (100% LINE, falla si baja)
```
## Modelo de ramificación

**Modelo de ramificación elegido:** GitFlow.

Elegimos **GitFlow** porque es el estándar más utilizado a nivel empresarial y el modelo más popular para el desarrollo de código. Además, **se adapta perfectamente al curso**, ya que cada evaluación (EP01, EP02, EP03) funciona como un hito estable.

Este modelo nos entrega beneficios clave:
* La rama `develop` permite integrar características de ambos integrantes sin arriesgar la estabilidad de `main`.
* La rama `hotfix/` facilita corregir errores urgentes en producción sin interrumpir el trabajo en desarrollo.
* Garantiza una **trazabilidad clara** al separar explícitamente el código estable del código en integración, cumpliendo con las rúbricas de la asignatura DevOps.

## Convenciones y Buenas Prácticas del Equipo

### Convención de Commits

Formato estándar: **tipo(alcance): descripcion-corta** (Todo en minúsculas y sin tildes).

* **feat**: Nueva funcionalidad. Ejemplo: `feat(ui): agregar pie de pagina`
* **fix**: Corrección de bug. Ejemplo: `fix(home): corregir titulo`
* **docs**: Documentación. Ejemplo: `docs: agregar changelog`
* **chore**: Tareas / CI. Ejemplo: `chore(ci): agregar workflow hola mundo`

### Naming de Ramas

* Formato permitido: **feature/<feature-name>** y **hotfix/<feature-name>**
* Reglas: Siempre en minúsculas y separadas por guiones.
* Ejemplos: `feature/pagina-presentacion`, `hotfix/titulo-pagina`.

### Flujo de Merge

* **Vía Pull Request**: Features y hotfix siempre entran por PR. Prohibido hacer push directo a `main` o `develop`.
* **Aprobación requerida**: Se necesita al menos 1 aprobación del compañero antes de fusionar.
* **Limpieza**: Usar merge commit o squash, y eliminar la rama inmediatamente después de fusionar.

### Estrategia de Revisión

* **Asignación**: El autor abre el PR y asigna obligatoriamente a un revisor.
* **Revisión activa**: El revisor comenta, aprueba o pide cambios. Nunca se fusiona un PR sin revisión previa.
* **Validación técnica**: Antes de cada PR, el autor debe confirmar que `mvn test` pasa sin errores y revisar las diferencias (diff).
* **Plantilla (Opcional)**: Uso de `PULL_REQUEST_TEMPLATE.md` para estandarizar la revisión en GitHub.

[![Hola Mundo CI](https://github.com/Benjamin1275/DEVOP/actions/workflows/hola-mundo.yml/badge.svg?branch=develop)](https://github.com/Benjamin1275/DEVOP/actions/workflows/hola-mundo.yml)