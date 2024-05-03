package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Education_Master")
public class EducationEntity {

	private long caseNum;

	@Id
	@GeneratedValue
	private Integer eductionId;
	private String higestDegree;
	private String universityName;
	private Integer graductionYear;

}
