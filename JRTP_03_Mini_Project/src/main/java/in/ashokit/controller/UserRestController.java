package in.ashokit.controller;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccountForm;
import in.ashokit.binding.UserForm;
import in.ashokit.service.UserMgmtService;

@RestController
public class UserRestController {

	@Autowired
	private UserMgmtService service;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
		String login = service.login(loginForm);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

	@GetMapping("/country")
	public Map<Integer, String> loadCountry() {
		Map<Integer, String> country = service.getCountry();
		return country;
	}

	@GetMapping("/state/{countryId}")
	public Map<Integer, String> loadState(@PathVariable Integer countryId) {
		return service.getState(countryId);

	}

	@GetMapping("city/{stateId}")
	public Map<Integer, String> loadCity(@PathVariable Integer stateId) {
		return service.getCity(stateId);
	}

	@GetMapping("/email/{email}")
	public String emailCheck(@PathVariable String email) {
		return service.checkEmail(email);
	}

	@PostMapping("/registerUser")
	public String regestration(@RequestBody UserForm userForm) {
		String registration = service.registration(userForm);
		return registration;
	}

	@PostMapping("/unlock")
	public ResponseEntity<String> unlockAcc(@RequestBody UnlockAccountForm accountForm) {
		String unlockAccount = service.unlockAccount(accountForm);
		return new ResponseEntity<>(unlockAccount, HttpStatus.OK);
	}

	@GetMapping("/forgotpwd/{email}")
	public ResponseEntity<String> forgotpwd(@PathVariable String email) {
		String forgotPwd = service.forgotPwd(email);
		return new ResponseEntity<>(forgotPwd, HttpStatus.OK);
	}

}
