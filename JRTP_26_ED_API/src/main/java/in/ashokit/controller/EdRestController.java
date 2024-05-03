package in.ashokit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.EligResponce;
import in.ashokit.service.EdService;

@RestController
public class EdRestController {

	
	private EdService service;
	
	@GetMapping("/eligibility/{caseNum}")
	public ResponseEntity<EligResponce> detrmin( @PathVariable Long  caseNum ){
		 EligResponce responce = service.determineEligibility(caseNum);
		return new ResponseEntity<>(responce,HttpStatus.OK);
	}

}
