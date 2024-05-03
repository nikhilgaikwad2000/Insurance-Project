package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="KIDS_MASTER")
public class KidEntity {
private long caseNum;

@Id
private Integer kidsId;
private String kidsName;
private Integer kidsAge;
private Integer ssn;
	


}
