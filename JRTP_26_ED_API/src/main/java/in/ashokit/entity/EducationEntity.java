package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Education_Master")
public class EducationEntity {

	private Long caseNum;

	@Id
	@GeneratedValue
	private Integer eductionId;
	private String higestDegree;
	private String universityName;
	private Integer graductionYear;

}
