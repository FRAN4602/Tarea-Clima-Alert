package ar.edu.utn.ba.ddsi.clima_alert.client;

import ar.edu.utn.ba.ddsi.clima_alert.config.WeatherApiProperties;
import ar.edu.utn.ba.ddsi.clima_alert.dto.WeatherApiResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
public class WeatherApiClient {
    private final RestTemplate restTemplate;
    private final WeatherApiProperties propiedades;

    public WeatherApiClient(RestTemplate restTemplate, WeatherApiProperties propiedades) {
        this.restTemplate = restTemplate;
        this.propiedades = propiedades;
    }

    public WeatherApiResponseDTO obtenerClimaActual() {
        URI uri = UriComponentsBuilder.fromUriString(propiedades.getBaseUrl())
                .path("/current.json")
                .queryParam("key", propiedades.getKey())
                .queryParam("q", propiedades.getUbicacion())
                .build()
                .toUri();
        return restTemplate.getForObject(uri, WeatherApiResponseDTO.class);
    }
}
