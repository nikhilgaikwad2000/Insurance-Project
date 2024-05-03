package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="City_Master")
public class City {
	@Id
private Integer cityId;
private String cityName;
private Integer stateId;

}
