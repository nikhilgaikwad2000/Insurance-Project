package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.Plan;

public interface PlanService {
    public String createPlan(Plan plan);
    public List<Plan> viewPlan();
    public String deletePlan(Integer planId);
}
