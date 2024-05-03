package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Plan;
import in.ashokit.repo.PlansRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlansRepo repo;

	@Override
	public String createPlan(Plan plan) {

		plan.setActiveStatus("Active");

		plan = repo.save(plan);
        if(plan.getPlanId()!=null) {
        	return"Plan Saved Successfully";
        }else {
        	return "Plan Failed to saved ";
        }
	
	}

	@Override
	public List<Plan> viewPlan() {
      //  List<Plan> plans = repo.findAll();
		List<Plan> plans = repo.findByActiveStatus("Active");
		return plans;
	}

	@Override
	public String deletePlan(Integer planId) {
       Optional<Plan> findById = repo.findById(planId);
       if(findById.isPresent()) {
    	   Plan plan = findById.get();
    	   plan.setActiveStatus("De-Active");
    	   repo.save(plan);
    	   return "Record Deleted";
       }else {
    	   return "No Record Found";
       }
		
	}

}
