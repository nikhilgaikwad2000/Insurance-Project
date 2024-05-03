package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CoTrgEntity;
import in.ashokit.entity.EdEligDtlsEntity;

public interface EdEligRepository extends JpaRepository<EdEligDtlsEntity, Serializable> {

	public EdEligDtlsEntity findByCaseNum(Long caseNum);
}
