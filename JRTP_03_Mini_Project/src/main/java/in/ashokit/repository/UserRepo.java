package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.User;

public interface UserRepo extends JpaRepository<User, Serializable> {
 
	//select * from user_master where email=?
	public User findByEmail(String email);
	
	public User findByEmailAndPwd(String email,String pwd);
}
