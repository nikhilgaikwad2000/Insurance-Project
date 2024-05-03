package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "INCOME_MASTER")
public class IncomeEntity {

	private long caseNum;
	
	@Id
	@GeneratedValue
	private Integer incomeID;
	private long monthalySalaryIncome;
	private long propertyIncome;
	private long rentIncome;

}
