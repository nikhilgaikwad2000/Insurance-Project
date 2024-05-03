package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="CASE_MASTER")
public class CaseEntity {

	@Id
	@GeneratedValue
	private long caseNum;
	private Integer appId;
	private Integer planId;

}
