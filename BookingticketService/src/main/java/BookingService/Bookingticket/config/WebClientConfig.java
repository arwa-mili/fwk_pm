package BookingService.Bookingticket.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder().filter(logRequest())
                .filter(logResponse())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> System.out.println(name + "=" + value)));
            return Mono.just(clientRequest);
        });
    }
    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofRequestProcessor(clientResponse -> {
            System.out.println("Request: " + clientResponse.method() + " " + clientResponse.url());
            clientResponse.headers().forEach((name, values) -> values.forEach(value -> System.out.println(name + "=" + value)));
            return Mono.just(clientResponse);
        });
    }
}