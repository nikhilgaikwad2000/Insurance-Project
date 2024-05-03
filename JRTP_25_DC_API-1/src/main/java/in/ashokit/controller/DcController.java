package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.EducationDetails;
import in.ashokit.binding.IncomeDetails;
import in.ashokit.binding.KidDetails;
import in.ashokit.binding.KidsInfo;
import in.ashokit.binding.PlanSelction;
import in.ashokit.binding.Summary;
import in.ashokit.repo.CaseRepo;
import in.ashokit.serviceImpl.DcServiceImpl;

@RestController
public class DcController {
	@Autowired
	private DcServiceImpl dcService;

	@GetMapping("/case/{appId}")
	public ResponseEntity<PlanSelction> createCase(@PathVariable Integer appId) {

		PlanSelction planSelResp = dcService.createPlan(appId);

		return new ResponseEntity<>(planSelResp, HttpStatus.OK);

	}

	@PostMapping("/planselection")
	public ResponseEntity<Long> selectPlan(@RequestBody PlanSelction planSelction) {

		long caseNum = dcService.updateCitizinePlan(planSelction);
		return new ResponseEntity<>(caseNum, HttpStatus.OK);
	}

	@PostMapping("/saveincome")
	public ResponseEntity<Long> saveIncome(@RequestBody IncomeDetails incomeDetails) {
		long caseNum = dcService.saveIncomeDetails(incomeDetails);

		return new ResponseEntity<>(caseNum, HttpStatus.OK);
	}

	@PostMapping("/saveeducation")
	public ResponseEntity<Long> saveEducation(@RequestBody EducationDetails educationDetails) {

		long caseNum = dcService.saveEducationDetails(educationDetails);
		return new ResponseEntity<>(caseNum, HttpStatus.OK);
	}
	
	@PostMapping("/savekids")
	public ResponseEntity<Summary> saveKidsDetails(@RequestBody KidsInfo kidsInfo){
		Summary summary = dcService.saveKidsDetails(kidsInfo);
		return new ResponseEntity<>(summary,HttpStatus.OK);
	}

}
