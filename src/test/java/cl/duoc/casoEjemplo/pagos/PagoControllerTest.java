package cl.duoc.casoEjemplo.pagos;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import cl.duoc.casoEjemplo.pagos.model.Pago;
import cl.duoc.casoEjemplo.pagos.service.PagoService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("null")
class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PagoService service;

    @NonNull
    private Pago recurso(Long id) {
        Pago r = new Pago();
        r.setId(id);
        r.setNombre("Demo");
        r.setOrden("valor");
        r.setMonto(BigDecimal.TEN);
        r.setEstado("valor");
        return r;
    }

    @NonNull
    private String body() throws Exception {
        return Objects.requireNonNull(objectMapper.writeValueAsString(recurso(null)));
    }

    @Test
    void listarDevuelve200YLista() throws Exception {
        when(service.findAll()).thenReturn(List.of(recurso(1L)));
        mockMvc.perform(get("/api/pagos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void obtenerExistenteDevuelve200() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(recurso(1L)));
        mockMvc.perform(get("/api/pagos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Demo"));
    }

    @Test
    void obtenerInexistenteDevuelve404() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/pagos/1")).andExpect(status().isNotFound());
    }

    @Test
    void crearValidoDevuelve201() throws Exception {
        when(service.create(any(Pago.class))).thenReturn(Objects.requireNonNull(recurso(1L)));
        mockMvc.perform(post("/api/pagos")
                .contentType("application/json")
                .content(body()))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void crearInvalidoDevuelve400() throws Exception {
        mockMvc.perform(post("/api/pagos")
                .contentType("application/json")
                .content("{\"nombre\":\"\"}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarExistenteDevuelve200() throws Exception {
        Pago actualizado = recurso(1L);
        actualizado.setNombre("Actualizado");
        when(service.update(any(Long.class), any(Pago.class)))
                .thenReturn(Optional.of(Objects.requireNonNull(actualizado)));
        mockMvc.perform(put("/api/pagos/1")
                .contentType("application/json")
                .content(body()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Actualizado"));
    }

    @Test
    void actualizarInexistenteDevuelve404() throws Exception {
        when(service.update(any(Long.class), any(Pago.class))).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/pagos/1")
                .contentType("application/json")
                .content(body()))
            .andExpect(status().isNotFound());
    }

    @Test
    void eliminarExistenteDevuelve204() throws Exception {
        when(service.delete(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/pagos/1")).andExpect(status().isNoContent());
    }

    @Test
    void eliminarInexistenteDevuelve404() throws Exception {
        when(service.delete(1L)).thenReturn(false);
        mockMvc.perform(delete("/api/pagos/1")).andExpect(status().isNotFound());
    }
}
