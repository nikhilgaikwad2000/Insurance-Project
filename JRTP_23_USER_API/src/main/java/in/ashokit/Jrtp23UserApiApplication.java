package in.ashokit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.client.WelcomeClient;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class Jrtp23UserApiApplication {

	@Autowired
	private WelcomeClient client;
	
	public static void main(String[] args) {
		SpringApplication.run(Jrtp23UserApiApplication.class, args);
	}
	
	@GetMapping("/user")
	public String getUser() {
		
		String WelcomeApiResp = client.invokeWelcomeApi();
		
		String UserApiResp="Hi this is Nikhil";
		
		
		return WelcomeApiResp+"  "+UserApiResp;
	}
 
}
