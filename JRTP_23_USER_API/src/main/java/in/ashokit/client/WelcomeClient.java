package in.ashokit.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="welcome-service.com")
public interface WelcomeClient {

	@GetMapping("/welcome")
	public String invokeWelcomeApi();

}
