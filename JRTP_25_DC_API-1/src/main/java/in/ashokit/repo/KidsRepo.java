package in.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.KidEntity;

public interface KidsRepo extends JpaRepository<KidEntity, Serializable> {
	public List<KidEntity> findByCaseNum(Long caseNum);
}
