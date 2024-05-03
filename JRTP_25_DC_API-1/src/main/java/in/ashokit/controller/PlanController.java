package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.PlanEntity;
import in.ashokit.service.PlanService;

@RestController
public class PlanController {

	@Autowired
	private PlanService planService;

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody PlanEntity planentity) {
		String status = planService.savePlan(planentity);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
