package edu.uady.gateway;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication.class, args);
    }

    @GetMapping("/test")
    public String test() {
        return "Test gateway";
    }


    @Bean
    @LoadBalanced
    public RouteLocator customRouterLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p
                        .path("/test1")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("https://google.mx"))
                .build();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public RouteLocatorBuilder routeLocatorBuild(ConfigurableApplicationContext context) {
        return new RouteLocatorBuilder(context);
    }

}
