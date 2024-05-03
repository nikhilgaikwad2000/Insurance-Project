package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CaseEntity;

public interface CaseRepo extends JpaRepository<CaseEntity, Serializable> {

}
