package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="State_Master")
public class State {
	@Id
private Integer stateId;
private String stateName;
private Integer countryId;
}
