package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.exception.AppException;

@RestController
public class CoustomerRestController {


	@GetMapping("/customer")
	public String getCustomerInfo() {

		System.out.println("method Started...");

		String msg = "Nikhil,India,23 years";
		
		try {
		String s=null;
		s.toUpperCase();
		}catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		System.out.println("method end....");

		return msg;
	}

}
