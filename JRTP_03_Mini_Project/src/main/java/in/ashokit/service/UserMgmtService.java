package in.ashokit.service;

import java.util.List;


import java.util.Map;

import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccountForm;
import in.ashokit.binding.UserForm;



public interface UserMgmtService {

	public String checkEmail (String email);
	public Map<Integer,String> getCountry();
	public Map<Integer,String> getState(Integer countryId);
	public Map<Integer,String> getCity(Integer stateId);
	public String registration (UserForm user);
	public String unlockAccount(UnlockAccountForm accountForm);
	public String login(LoginForm loginform);
	public String forgotPwd(String email);
	
	
	
}
