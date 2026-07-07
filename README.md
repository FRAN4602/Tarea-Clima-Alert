# Climalert

Servicio autónomo de monitoreo climático y envío automático de alertas por correo electrónico.

## ¿Qué hace?

Climalert corre en segundo plano, sin interfaz gráfica, con dos tareas programadas independientes:

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
| **Lombok** | Reducción de boilerplate en DTOs y entidades |

