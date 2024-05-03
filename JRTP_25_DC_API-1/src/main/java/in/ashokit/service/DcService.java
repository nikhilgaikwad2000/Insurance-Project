package in.ashokit.service;

import in.ashokit.binding.EducationDetails;
import in.ashokit.binding.IncomeDetails;
import in.ashokit.binding.KidsInfo;
import in.ashokit.binding.PlanSelction;
import in.ashokit.binding.Summary;

public interface DcService {
  
	
	public PlanSelction createPlan(Integer appId);
	
	public long updateCitizinePlan(PlanSelction planSelction);
	
	public long saveIncomeDetails(IncomeDetails incomeDetails);
	
	public long saveEducationDetails(EducationDetails educationDetails);
	
	public Summary saveKidsDetails(KidsInfo kidsInfo);
}
