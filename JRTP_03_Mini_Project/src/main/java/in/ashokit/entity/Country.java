package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Country_Master")
public class Country {
	@Id
 private Integer countryId;
 private String countryName;
}
