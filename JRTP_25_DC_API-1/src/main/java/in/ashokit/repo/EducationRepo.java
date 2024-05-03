package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.EducationEntity;

public interface EducationRepo extends JpaRepository<EducationEntity, Serializable> {

	public EducationEntity findByCaseNum(Long caseNum);
}
