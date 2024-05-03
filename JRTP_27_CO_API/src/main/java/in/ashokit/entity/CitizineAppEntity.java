package in.ashokit.entity;

import java.time.LocalDate;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@jakarta.persistence.Entity
@Data
@jakarta.persistence.Table(name = "CITIZINE_APP")
public class CitizineAppEntity {

	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue
	private Integer appId;
	private String fullName;
	private Long mobileNum;
	private LocalDate dob;
	private String emailId;
	private String gender;
	private Integer ssn;

	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;

	private Integer createBy;
	private Integer updateBy;

}
