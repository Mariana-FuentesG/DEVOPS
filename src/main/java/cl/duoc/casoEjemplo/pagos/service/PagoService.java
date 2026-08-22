package cl.duoc.casoEjemplo.pagos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import cl.duoc.casoEjemplo.pagos.model.Pago;
import cl.duoc.casoEjemplo.pagos.repository.PagoRepository;

@Service
public class PagoService {

    private final PagoRepository repository;

    public PagoService(PagoRepository repository) {
        this.repository = repository;
    }

    public List<Pago> findAll() {
        return repository.findAll();
    }

    public Optional<Pago> findById(Long id) {
        return repository.findById(id);
    }

    public Pago create(Pago recurso) {
        return repository.save(recurso);
    }

    public Optional<Pago> update(Long id, Pago datos) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(datos.getNombre());
            existente.setOrden(datos.getOrden());
            existente.setMonto(datos.getMonto());
            existente.setEstado(datos.getEstado());
            return repository.save(existente);
        });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(existente -> {
            repository.delete(existente);
            return true;
        }).orElse(false);
    }
}
