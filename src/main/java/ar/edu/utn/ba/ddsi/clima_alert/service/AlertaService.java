package ar.edu.utn.ba.ddsi.clima_alert.service;

import ar.edu.utn.ba.ddsi.clima_alert.domain.RegistroClimatico;
import ar.edu.utn.ba.ddsi.clima_alert.repository.RegistroClimaticoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlertaService {
    private static final double TEMP_UMBRAL = 35.0;
    private static final double HUMEDAD_UMBRAL = 60.0;

    private final RegistroClimaticoRepository repository;
    private final EmailService emailService;
    private LocalDateTime ultimoRegistroAlertado;

    public AlertaService(RegistroClimaticoRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 60000) // 1 minuto
    public void analizarUltimoClima() {
        repository.obtenerUltimo().ifPresent(registro -> {
            if (esCondicionCritica(registro) && esRegistroNuevo(registro)) {
                emailService.enviarAlerta(registro);
                ultimoRegistroAlertado = registro.getFechaHora();
            }
        });
    }

    private boolean esCondicionCritica(RegistroClimatico r) {
        return r.getTemperaturaC() > TEMP_UMBRAL && r.getHumedad() > HUMEDAD_UMBRAL;
    }

    private boolean esRegistroNuevo(RegistroClimatico r) {
        return !r.getFechaHora().equals(ultimoRegistroAlertado);
    }
}
