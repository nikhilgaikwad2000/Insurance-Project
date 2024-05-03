package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
