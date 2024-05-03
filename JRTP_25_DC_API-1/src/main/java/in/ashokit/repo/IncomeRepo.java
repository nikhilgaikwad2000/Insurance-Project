package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.IncomeEntity;

public interface IncomeRepo extends JpaRepository<IncomeEntity, Serializable> {
 public IncomeEntity findByCaseNum(Long caseNum);
}
