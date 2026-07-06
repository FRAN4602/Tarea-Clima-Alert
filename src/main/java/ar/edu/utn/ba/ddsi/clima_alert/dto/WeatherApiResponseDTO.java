package ar.edu.utn.ba.ddsi.clima_alert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponseDTO(Current current) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Current(
            @JsonProperty("temp_c")
            double tempC,
            double humidity,
            Condition condition,
            @JsonProperty("feelslike_c")
            double feelslikeC,
            @JsonProperty("wind_kph")
            double windKph,
            @JsonProperty("pressure_mb")
            double pressureMb,
            @JsonProperty("last_updated")
            String lastUpdated
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Condition(String text) {}
}
