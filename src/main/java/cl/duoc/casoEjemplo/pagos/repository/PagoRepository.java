package cl.duoc.casoEjemplo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.casoEjemplo.pagos.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
