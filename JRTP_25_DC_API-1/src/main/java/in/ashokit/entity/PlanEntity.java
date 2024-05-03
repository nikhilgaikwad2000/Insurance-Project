package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="PLAN_MASTER")
public class PlanEntity {
	
@Id	
@GeneratedValue
private Integer palnId;
private String planName;	
}
