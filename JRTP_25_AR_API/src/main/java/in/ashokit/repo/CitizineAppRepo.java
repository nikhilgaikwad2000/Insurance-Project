package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CitizineAppEntity;

public interface CitizineAppRepo extends JpaRepository<CitizineAppEntity, Serializable> {

}
