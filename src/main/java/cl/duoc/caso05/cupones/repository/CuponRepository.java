package cl.duoc.caso05.cupones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.caso05.cupones.model.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, Long> {
}
