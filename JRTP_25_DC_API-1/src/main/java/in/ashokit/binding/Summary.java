package in.ashokit.binding;

import lombok.Data;

@Data
public class Summary {

	private String planName;
	private String fullName;
	private Integer ssn;
	private PlanSelction planInfo;
	private IncomeDetails incomeInfo;
	private EducationDetails eductionInfo;
	private  KidsInfo kidsInfo;
	

}
