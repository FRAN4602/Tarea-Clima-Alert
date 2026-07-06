package ar.edu.utn.ba.ddsi.clima_alert.mapper;

import ar.edu.utn.ba.ddsi.clima_alert.domain.RegistroClimatico;
import ar.edu.utn.ba.ddsi.clima_alert.dto.WeatherApiResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegistroClimaticoMapper {
    public RegistroClimatico aDominio(WeatherApiResponseDTO dto) {
        var c = dto.current();
        return new RegistroClimatico(
                LocalDateTime.now(),
                c.tempC(),
                c.humidity(),
                c.condition().text(),
                c.feelslikeC(),
                c.windKph(),
                c.pressureMb()
        );
    }
}
