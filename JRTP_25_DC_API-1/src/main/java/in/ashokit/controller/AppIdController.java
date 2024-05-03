package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.CitizineAppEntity;
import in.ashokit.service.AppIdService;

@RestController
public class AppIdController {

	@Autowired
	private AppIdService appIdService;

	@PostMapping("/savecitizendata")
	public ResponseEntity<String> saveAppId(@RequestBody CitizineAppEntity appId) {

		String status = appIdService.saveAppID(appId);

		return new ResponseEntity<>(status, HttpStatus.OK);

	}
	
	
}
