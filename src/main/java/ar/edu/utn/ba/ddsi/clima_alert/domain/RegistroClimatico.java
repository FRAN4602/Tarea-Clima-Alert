package ar.edu.utn.ba.ddsi.clima_alert.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegistroClimatico {
    private final LocalDateTime fechaHora;
    private final double temperaturaC;
    private final double humedad;
    private final String condicion;
    private final double sensacionTermicaC;
    private final double vientoKph;
    private final double presionMb;
}
