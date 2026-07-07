# Climalert

Servicio autónomo de monitoreo climático y envío automático de alertas por correo electrónico, desarrollado como práctica de la cátedra Diseño de Sistemas de Información (UTN FRBA).

## ¿Qué hace?

Climalert corre en segundo plano, sin interfaz gráfica ni endpoints propios, con dos tareas programadas independientes:

- **Cada 5 minutos**: consulta el clima actual de Buenos Aires vía [WeatherAPI](https://www.weatherapi.com/) (`/current.json`) y almacena el resultado localmente para registro histórico.
- **Cada 1 minuto**: analiza el último registro guardado y evalúa si se cumple la condición de alerta.

**Condición de alerta**: temperatura mayor a 35°C **y** humedad superior a 60% (ambas condiciones simultáneamente).

Cuando se detecta una condición crítica, el sistema envía un correo con el detalle completo del clima a:
- admin@clima.com
- emergencias@clima.com
- meteorologia@clima.com

Para evitar mails duplicados, solo se dispara una alerta por cada nuevo registro climático (no se reenvía en cada corrida del análisis mientras persista el mismo dato).

## Tecnologías

| Tecnología | Uso |
|---|---|
| **Java 21** (o superior) | Lenguaje y runtime |
| **Spring Boot** | Aplicación, inyección de dependencias, scheduling (`@Scheduled`) y cliente HTTP |
| **RestTemplate** | Consumo de la API externa de clima |
| **Jackson** | Deserialización del JSON de WeatherAPI a DTOs |
| **Spring Mail** (`JavaMailSender`) | Envío de correos de alerta |
| **Lombok** | Reducción de boilerplate en DTOs y entidades |
| **JUnit 5 + Mockito** | Tests unitarios de la lógica de negocio |

## Estructura del proyecto

```
config/       -> RestTemplateConfig, WeatherApiProperties, EmailProperties
client/       -> WeatherApiClient (integración con WeatherAPI)
dto/          -> WeatherApiResponseDTO (mapea el JSON externo)
domain/       -> RegistroClimatico (entidad de dominio, inmutable)
repository/   -> RegistroClimaticoRepository + implementación en memoria
mapper/       -> RegistroClimaticoMapper (DTO externo -> dominio)
service/      -> WeatherFetchService, AlertaService, EmailService
```

## Variables de entorno necesarias

El proyecto no incluye credenciales en el repositorio. Antes de correrlo, configurá:

| Variable | Descripción |
|---|---|
| `WEATHERAPI_KEY` | API key obtenida en [weatherapi.com](https://www.weatherapi.com/) |
| `MAIL_USERNAME` | Cuenta de correo remitente (ej. Gmail) |
| `MAIL_PASSWORD` | App password de esa cuenta (no la contraseña real) |

En IntelliJ: **Run/Debug Configurations → Environment variables**.

## Cómo correrlo

```bash
mvn spring-boot:run
```

## Tests

```bash
mvn test
```

Incluye tests unitarios (Mockito) para la lógica de umbrales, deduplicación de alertas, mapeo de DTOs y manejo de errores de red — no dependen de credenciales ni conectividad externa.
