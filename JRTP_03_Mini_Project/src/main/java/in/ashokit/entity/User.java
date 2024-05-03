package in.ashokit.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="User_Dtls")
public class User {
	
	@Id
	@GeneratedValue
 private  Integer userId;
 private String fName;
 private String lName;
 private String email;
 private Long phNo;
 private LocalDate date;
 private String gender;
 private Integer country;
 private Integer state;
 private Integer city;
 private String pwd;
 private String accStatus;
 
}
