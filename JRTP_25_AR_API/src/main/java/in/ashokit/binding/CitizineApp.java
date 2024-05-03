package in.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizineApp {

	private String fullName;
	private Long mobileNum;
	private LocalDate dob;
	private String emailId;
	private String gender;
	private Integer ssn;

	private Integer createBy;
	private Integer updateBy;
}
