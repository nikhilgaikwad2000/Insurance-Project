package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.binding.CitizinePlan;

public interface Citizinerepository extends JpaRepository<CitizinePlan, Serializable> {

	@Query("select distinct(planName) from CitizinePlan")
	public List<String> getPlanNames();

	@Query("select distinct(planStatus) from CitizinePlan")
	public List<String> getplanStatus();

	}