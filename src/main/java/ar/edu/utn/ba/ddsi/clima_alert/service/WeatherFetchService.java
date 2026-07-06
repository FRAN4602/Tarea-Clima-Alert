package ar.edu.utn.ba.ddsi.clima_alert.service;

import ar.edu.utn.ba.ddsi.clima_alert.client.WeatherApiClient;
import ar.edu.utn.ba.ddsi.clima_alert.dto.WeatherApiResponseDTO;
import ar.edu.utn.ba.ddsi.clima_alert.mapper.RegistroClimaticoMapper;
import ar.edu.utn.ba.ddsi.clima_alert.repository.RegistroClimaticoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherFetchService {
    private final WeatherApiClient client;
    private final RegistroClimaticoMapper mapper;
    private final RegistroClimaticoRepository repository;

    public WeatherFetchService(WeatherApiClient client, RegistroClimaticoMapper mapper,
                               RegistroClimaticoRepository repository) {
        this.client = client;
        this.mapper = mapper;
        this.repository = repository;
    }

    @Scheduled(fixedRate = 300000) // 5 minutos
    public void obtenerYAlmacenarClima() {
        WeatherApiResponseDTO respuesta = client.obtenerClimaActual();
        repository.guardar(mapper.aDominio(respuesta));
    }
}
