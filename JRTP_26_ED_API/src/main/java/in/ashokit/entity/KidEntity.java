package in.ashokit.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="KIDS_MASTER")
public class KidEntity {
private Long caseNum;

@Id
private Integer kidsId;
private String kidsName;
private Integer kidsAge;
private LocalDate kidsDob;
private Integer ssn;
	


}
