package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class Jrtp21AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jrtp21AdminServerApplication.class, args);
	}

}
