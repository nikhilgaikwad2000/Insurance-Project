
package in.ashokit.binding;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="citizens_plans_info")
public class CitizinePlan {
  
	@Id
	@GeneratedValue
	private Integer cid;
	private String cname;
	private String email;
	private long phno;
	private String gender;
	private long ssn;
	private String planName;
	private String planStatus;
}
