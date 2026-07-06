package ar.edu.utn.ba.ddsi.clima_alert.repository;

import ar.edu.utn.ba.ddsi.clima_alert.domain.RegistroClimatico;
import java.util.Optional;

public interface RegistroClimaticoRepository {
    void guardar(RegistroClimatico registro);
    Optional<RegistroClimatico> obtenerUltimo();
}
