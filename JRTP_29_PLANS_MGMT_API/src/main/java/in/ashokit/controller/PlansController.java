package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Plan;
import in.ashokit.service.PlanService;

@RestController
public class PlansController {

	@Autowired
	private PlanService service;

	@PostMapping("/savePlan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String status = service.createPlan(plan);
		return new ResponseEntity<>(status, HttpStatus.OK);

	}

	@GetMapping("/getPlan")
	public ResponseEntity<List<Plan>> getAllPlan() {
		List<Plan> list = service.viewPlan();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@DeleteMapping("/deletePlan/{PlanId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer PlanId) {

		String status = service.deletePlan(PlanId);
		return new ResponseEntity<>(status, HttpStatus.OK);

	}
}
