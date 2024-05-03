package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.exception.AppException;

@RestController
public class DemoController {

	@GetMapping("/")
	public String getMsg() {

		System.out.println("method Started...");

		String msg = "Hello,good morning";
		
		try {
		int i=10/0;
		}catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		System.out.println("method end....");

		return msg;
	}
}
