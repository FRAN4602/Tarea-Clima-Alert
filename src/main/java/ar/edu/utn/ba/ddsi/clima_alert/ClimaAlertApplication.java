package ar.edu.utn.ba.ddsi.clima_alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClimaAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimaAlertApplication.class, args);
	}

}
