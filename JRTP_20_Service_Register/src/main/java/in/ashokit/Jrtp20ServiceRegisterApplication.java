package in.ashokit;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Jrtp20ServiceRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jrtp20ServiceRegisterApplication.class, args);
	}

}
