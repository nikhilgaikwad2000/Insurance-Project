package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CO_TRIGGERS")
public class CoTrgEntity {

	@Id
	@GeneratedValue
	private Integer coTrgId;

	private Long caseNum;
	private byte[] pdf;
	private String trgStatus;
}
