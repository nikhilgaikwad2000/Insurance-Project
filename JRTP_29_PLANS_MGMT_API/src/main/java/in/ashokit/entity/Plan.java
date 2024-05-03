package in.ashokit.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PLAN")
public class Plan {

	@Id
	@GeneratedValue
	private Integer planId;
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private String planCategory;
	private String activeStatus;

}
