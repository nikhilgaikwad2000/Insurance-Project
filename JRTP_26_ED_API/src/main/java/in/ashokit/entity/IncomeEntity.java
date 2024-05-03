package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
