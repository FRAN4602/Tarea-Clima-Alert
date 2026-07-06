package ar.edu.utn.ba.ddsi.clima_alert.service;

import ar.edu.utn.ba.ddsi.clima_alert.config.EmailProperties;
import ar.edu.utn.ba.ddsi.clima_alert.domain.RegistroClimatico;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;

    public EmailService(JavaMailSender mailSender, EmailProperties emailProperties) {
        this.mailSender = mailSender;
        this.emailProperties = emailProperties;
    }

    public void enviarAlerta(RegistroClimatico r) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(emailProperties.getDestinatarios().toArray(new String[0]));
        mensaje.setSubject("Alerta climática detectada");
        mensaje.setText("""
            Se detectó una condición climática crítica:

            Fecha/hora: %s
            Temperatura: %.1f°C
            Humedad: %.1f%%
            Condición: %s
            Sensación térmica: %.1f°C
            Viento: %.1f kph
            Presión: %.1f mb
            """.formatted(r.getFechaHora(), r.getTemperaturaC(), r.getHumedad(),
                r.getCondicion(), r.getSensacionTermicaC(),
                r.getVientoKph(), r.getPresionMb()));
        mailSender.send(mensaje);
    }
}
