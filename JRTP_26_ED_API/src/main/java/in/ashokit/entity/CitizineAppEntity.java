package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
