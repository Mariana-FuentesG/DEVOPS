package cl.duoc.casoEjemplo.pagos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;


@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Column
    private String orden;
    @Column
    private BigDecimal monto;
    @Column
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrden() { return orden; }

    public void setOrden(String orden) { this.orden = orden; }

    public BigDecimal getMonto() { return monto; }

    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

}
