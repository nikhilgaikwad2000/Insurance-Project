package in.ashokit.binding;


import java.util.Map;

import lombok.Data;

@Data
public class PlanSelction {

	private long caseNum;
	private Integer planId;
	
	private Map<Integer, String> plansInfo;
}
