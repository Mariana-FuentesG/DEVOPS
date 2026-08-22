package cl.duoc.casoEjemplo.pagos;

import org.junit.jupiter.api.Test;
import cl.duoc.casoEjemplo.pagos.config.OpenApiConfig;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiConfigTest {

    @Test
    void beanOpenApiGenerado() {
        assertThat(new OpenApiConfig().customOpenAPI()).isNotNull();
    }
}
