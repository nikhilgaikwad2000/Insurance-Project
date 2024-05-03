package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.State;

public interface StateRepo extends JpaRepository<State, Serializable> {
    
	// select * from state_master where stateid=?
	 List<State> findByCountryId(Integer countryId);
}
