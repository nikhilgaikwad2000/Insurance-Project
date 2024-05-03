package in.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {
	private String fName;
	 private String lName;
	 private String email;
	 private Long phNo;
	 private LocalDate date;
	 private String gender;
	 private Integer country;
	 private Integer state;
	 private Integer city;
}
