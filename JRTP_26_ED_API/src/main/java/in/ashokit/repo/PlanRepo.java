package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Serializable> {
    
}
