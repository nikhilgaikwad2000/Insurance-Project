package in.ashokit.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.PlanEntity;
import in.ashokit.repo.PlanRepo;
import in.ashokit.service.PlanService;
@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo;
	
	@Override
	public String savePlan(PlanEntity planetity) {
		
		planetity  = planRepo.save(planetity);
		return "plan data saved successfully....!!!";
	}



}
