package cl.duoc.caso05.cupones;

import org.junit.jupiter.api.Test;
import cl.duoc.caso05.cupones.config.OpenApiConfig;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiConfigTest {

    @Test
    void beanOpenApiGenerado() {
        assertThat(new OpenApiConfig().customOpenAPI()).isNotNull();
    }
}
