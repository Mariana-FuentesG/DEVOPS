# language: es
Característica: Servicio Cupones (microservicio cupones del caso caso05)
  Los escenarios validan el contrato REST del microservicio alineado a sus endpoints.

  Escenario: el listado del recurso responde 200
    Dado el servicio "Cupones" está disponible
    Cuando consulto el listado de "cupones"
    Entonces el listado responde con código 200

  Escenario: ciclo de vida completo del recurso
    Dado un nuevo "cupon" con nombre "hola-cucumber"
    Cuando consulto el "cupon" recién creado
    Entonces el recurso tiene nombre "hola-cucumber" y código 200
    Cuando actualizo el "cupon" con nombre "cucumber-actualizado"
    Entonces el recurso queda con nombre "cucumber-actualizado" y código 200
    Cuando elimino el "cupon"
    Entonces la eliminación responde con código 204
    Y al consultar el "cupon" eliminado responde 404
