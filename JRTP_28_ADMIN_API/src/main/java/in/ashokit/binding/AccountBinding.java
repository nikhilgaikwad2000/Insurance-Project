package in.ashokit.binding;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccountBinding {
  @Id
  @GeneratedValue
	private Integer id;
	private String fullName;
	private String eMail;
	private Long mobileNum;
	private String gender;
	private long ssn;

}
