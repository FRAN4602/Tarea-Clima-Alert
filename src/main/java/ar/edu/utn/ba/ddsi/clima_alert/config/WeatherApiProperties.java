package ar.edu.utn.ba.ddsi.clima_alert.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weatherapi")
@Data
public class WeatherApiProperties {
    private String baseUrl;
    private String key;
    private String ubicacion;
}
