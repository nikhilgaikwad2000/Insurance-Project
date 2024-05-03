package in.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CoTrgEntity;

public interface CoTriggerRepository extends JpaRepository<CoTrgEntity, Serializable> {
     
	public List<CoTrgEntity>  findByTrgStatus(String Status);
	
	public CoTrgEntity findByCaseNum(Long caseNum);
}
