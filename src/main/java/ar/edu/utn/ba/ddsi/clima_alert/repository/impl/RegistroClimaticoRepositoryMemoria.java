package ar.edu.utn.ba.ddsi.clima_alert.repository.impl;

import ar.edu.utn.ba.ddsi.clima_alert.domain.RegistroClimatico;
import ar.edu.utn.ba.ddsi.clima_alert.repository.RegistroClimaticoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class RegistroClimaticoRepositoryMemoria implements RegistroClimaticoRepository {
    private final List<RegistroClimatico> registros = new CopyOnWriteArrayList<>();

    @Override
    public void guardar(RegistroClimatico registro) {
        registros.add(registro);
    }

    @Override
    public Optional<RegistroClimatico> obtenerUltimo() {
        if (registros.isEmpty()) return Optional.empty();
        return Optional.of(registros.getLast());
    }
}
