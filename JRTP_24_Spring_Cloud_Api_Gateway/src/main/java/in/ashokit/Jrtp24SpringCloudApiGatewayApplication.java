package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Jrtp24SpringCloudApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jrtp24SpringCloudApiGatewayApplication.class, args);
	}

}
