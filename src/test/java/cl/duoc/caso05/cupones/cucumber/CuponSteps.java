package cl.duoc.caso05.cupones.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import cl.duoc.caso05.cupones.model.Cupon;

import static org.assertj.core.api.Assertions.assertThat;

public class CuponSteps {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    private Long id;
    private ResponseEntity<Cupon> respuesta;
    private ResponseEntity<Void> respuestaVoid;

    private String url() {
        return "http://localhost:" + port + "/api/cupones";
    }

    private HttpEntity<Map<String, String>> body(String nombre) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(Map.of("nombre", nombre), headers);
    }

    @Given("el servicio {string} está disponible")
    public void servicioDisponible(String servicio) {
        ResponseEntity<Cupon[]> listado = rest.getForEntity(url(), Cupon[].class);
        assertThat(listado.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @When("consulto el listado de {string}")
    public void consultarListado(String recurso) {
        ResponseEntity<Cupon[]> listado = rest.getForEntity(url(), Cupon[].class);
        assertThat(listado.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Then("el listado responde con código {int}")
    public void listadoCodigo(int codigo) {
        assertThat(codigo).isEqualTo(200);
    }

    @Given("un nuevo {string} con nombre {string}")
    public void crearRecurso(String recurso, String nombre) {
        ResponseEntity<Cupon> creado = rest.postForEntity(
                url(), body(nombre), Cupon.class);
        assertThat(creado.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        id = creado.getBody().getId();
    }

    @When("consulto el {string} recién creado")
    public void consultarCreado(String recurso) {
        respuesta = rest.getForEntity(url() + "/" + id, Cupon.class);
    }

    @Then("el recurso tiene nombre {string} y código {int}")
    public void recursoConNombre(String nombre, int codigo) {
        assertThat(respuesta.getStatusCode().value()).isEqualTo(codigo);
        assertThat(respuesta.getBody().getNombre()).isEqualTo(nombre);
    }

    @When("actualizo el {string} con nombre {string}")
    public void actualizar(String recurso, String nombre) {
        respuesta = rest.exchange(url() + "/" + id, HttpMethod.PUT, body(nombre), Cupon.class);
    }

    @Then("el recurso queda con nombre {string} y código {int}")
    public void recursoActualizado(String nombre, int codigo) {
        assertThat(respuesta.getStatusCode().value()).isEqualTo(codigo);
        assertThat(respuesta.getBody().getNombre()).isEqualTo(nombre);
    }

    @When("elimino el {string}")
    public void eliminar(String recurso) {
        respuestaVoid = rest.exchange(url() + "/" + id, HttpMethod.DELETE, null, Void.class);
    }

    @Then("la eliminación responde con código {int}")
    public void eliminarCodigo(int codigo) {
        assertThat(respuestaVoid.getStatusCode().value()).isEqualTo(codigo);
    }

    @Then("al consultar el {string} eliminado responde {int}")
    public void consultarEliminado(String recurso, int codigo) {
        ResponseEntity<Cupon> noExiste = rest.getForEntity(url() + "/" + id, Cupon.class);
        assertThat(noExiste.getStatusCode().value()).isEqualTo(codigo);
    }
}
