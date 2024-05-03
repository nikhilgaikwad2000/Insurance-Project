package in.ashokit.entity;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "CITIZINE_APP")
public class CitizineAppEntity {

	@Id
	@GeneratedValue
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
