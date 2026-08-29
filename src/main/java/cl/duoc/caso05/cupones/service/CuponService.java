package cl.duoc.caso05.cupones.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import cl.duoc.caso05.cupones.model.Cupon;
import cl.duoc.caso05.cupones.repository.CuponRepository;

@Service
public class CuponService {

    private final CuponRepository repository;

    public CuponService(CuponRepository repository) {
        this.repository = repository;
    }

    public List<Cupon> findAll() {
        return repository.findAll();
    }

    public Optional<Cupon> findById(Long id) {
        return repository.findById(id);
    }

    public Cupon create(Cupon recurso) {
        return repository.save(recurso);
    }

    public Optional<Cupon> update(Long id, Cupon datos) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(datos.getNombre());
            existente.setCodigo(datos.getCodigo());
            existente.setPorcentaje(datos.getPorcentaje());
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
