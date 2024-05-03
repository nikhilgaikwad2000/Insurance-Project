package in.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Plan;

public interface PlansRepo extends JpaRepository<Plan, Serializable> {

	public List<Plan> findByActiveStatus(String status);
}
