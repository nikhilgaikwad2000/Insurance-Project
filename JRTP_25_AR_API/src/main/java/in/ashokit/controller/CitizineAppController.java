package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.CitizineApp;
import in.ashokit.service.CitizineAppRegistrationService;

@RestController
public class CitizineAppController {
	@Autowired
	private CitizineAppRegistrationService service;

	@PostMapping("/citizineApp")
	public ResponseEntity<String> registeruser(@RequestBody CitizineApp app) {

		String responce = service.regestration(app);

		return new ResponseEntity<String>(responce, HttpStatus.OK);

	}
}
