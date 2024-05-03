package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.City;
import in.ashokit.entity.State;

public interface CityRepo extends JpaRepository<City, Serializable> {

	 public List<City> findByStateId(Integer StateId);
	 
}
