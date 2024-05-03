package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Jrtp25ArApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jrtp25ArApiApplication.class, args);
	}

}
