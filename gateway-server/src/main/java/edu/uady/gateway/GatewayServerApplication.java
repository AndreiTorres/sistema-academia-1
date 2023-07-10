package edu.uady.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}


	@GetMapping("/error")
	public String erro(){
		return "microservicio de uady no disponible por el momento";
	}

	@Bean
	@LoadBalanced
	public RouteLocator router(RouteLocatorBuilder builder) {
		return builder.routes()

				.build();
	}

	@Bean
	@Primary
	@ConditionalOnMissingBean
	public RouteLocatorBuilder routeLocatorBuild(ConfigurableApplicationContext context) {
		return new RouteLocatorBuilder(context);
	}

}
