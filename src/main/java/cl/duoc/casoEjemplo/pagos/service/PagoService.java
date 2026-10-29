package cl.duoc.casoEjemplo.pagos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
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

    public Optional<Pago> findById(@NonNull Long id) {
        return repository.findById(id);
    }

    @NonNull
    public Pago create(@NonNull Pago recurso) {
        return repository.save(recurso);
    }

    public Optional<Pago> update(@NonNull Long id, @NonNull Pago datos) {
        return repository.findById(id).map((@NonNull Pago existente) -> {
            existente.setNombre(datos.getNombre());
            existente.setOrden(datos.getOrden());
            existente.setMonto(datos.getMonto());
            existente.setEstado(datos.getEstado());
            return repository.save(existente);
        });
    }

    public boolean delete(@NonNull Long id) {
        return repository.findById(id).map((@NonNull Pago existente) -> {
            repository.delete(existente);
            return true;
        }).orElse(false);
    }
}
