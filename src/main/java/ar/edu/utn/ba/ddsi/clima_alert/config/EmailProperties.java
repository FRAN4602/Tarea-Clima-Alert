package ar.edu.utn.ba.ddsi.clima_alert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "climalert.email")
@Data
public class EmailProperties {
    private List<String> destinatarios;
}
