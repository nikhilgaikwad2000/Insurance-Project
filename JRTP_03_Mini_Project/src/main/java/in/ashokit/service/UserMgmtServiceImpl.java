package in.ashokit.service;

import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccountForm;
import in.ashokit.binding.UserForm;
import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.User;
import in.ashokit.repository.CityRepo;
import in.ashokit.repository.CountryRepo;
import in.ashokit.repository.StateRepo;
import in.ashokit.repository.UserRepo;
import in.ashokit.utils.EmailUtils;
@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String checkEmail(String email) {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			return "Duplicate";
		} else {
			return "UNIQUE";

		}
	}

	@Override
	public Map<Integer, String> getCountry() {

		List<Country> countryList = countryRepo.findAll();

		Map<Integer, String> countries = new HashMap<>();

		for (Country country : countryList) {
			countries.put(country.getCountryId(), country.getCountryName());
		}

		return countries;
	}

	@Override
	public Map<Integer, String> getState(Integer countryId) {

	    List<State> stateList = stateRepo.findByCountryId(countryId);
	    Map<Integer, String> states = new HashMap<>();

	    for (State state : stateList) {
	        states.put(state.getStateId(), state.getStateName());
	    }

	    return states;
	}

	@Override
	public Map<Integer, String> getCity(Integer stateId) {
		List<City> cityList = cityRepo.findByStateId(stateId);
		Map<Integer, String> citys = new HashMap<>();
		for (City city : cityList) {
			citys.put(city.getCityId(), city.getCityName());

		}
		return citys;
	}

	@Override
	public String registration(UserForm userForm) {

	
		// copy data binding to entity obj
		User entity = new User();
		BeanUtils.copyProperties(userForm, entity);

		
		
		// generate and set random password
		String generatePassword = generatePassword();
		entity.setPwd(generatePassword);
		// set acc status locked
		entity.setAccStatus("locked");

		userRepo.save(entity);

		// send email to unlock account

		String to = userForm.getEmail();
		String subject = "Registratio Email";
		String body = readEmailBody("REG_EMAIL_BODY.txt", entity);
		emailUtils.sendEmail(to, subject, body);

		return "User Account is created";
	}

	// @Override
	// public String unlockAccount(UnlockAccountForm accountForm) {
//		// get email
//		String email = accountForm.getEmail();
//		
//		User user = userRepo.findByEmail(email);
//		if(user.getPwd().equals(accountForm.getTempPwd())) {
//			user.setPwd(accountForm.getNewPwd());
//			user.setAccStatus("UNLOCKED");
//			userRepo.save(user);
//			return "ACCOUNT UNLOCKED";
//		}
//		return "Invalid temporary password";

	@Override
	public String unlockAccount(UnlockAccountForm accountForm) {
		// get email, temporary password, and new password
		String email = accountForm.getEmail();
		String tempPwd = accountForm.getTempPwd();
		String newPwd = accountForm.getNewPwd();

		// retrieve user for email
		User user = userRepo.findByEmail(email);

		if (!user.getPwd().equals(tempPwd)) {
			// temporary password does not match
			return "error: invalid temporary password";
		}

		// temporary password matches, update password and account status
		user.setPwd(newPwd);
		user.setAccStatus("unlocked");
		userRepo.save(user);

		return "success: account unlocked";

	}

	@Override

	public String login(LoginForm loginform) {

		User user = userRepo.findByEmailAndPwd(loginform.getEmail(), loginform.getPwd());
		if (user == null) {
			return "Invalied credential";
		}
		if (user.getAccStatus().equals("LOCKED")) {
			return "Account Locked";
		}
		return "SUCCESS";

	}

	@Override
	public String forgotPwd(String email) {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			return "User not found";
		}

		String subject = "Recover Password";
		String body = readEmailBody("FORGOT_PWD_EMAIL_BODY.txt", user);
		emailUtils.sendEmail(email, subject, body);

		return "Password sent to register email";
	}

	private String generatePassword() {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		Random rand = new Random();
		StringBuilder password = new StringBuilder();
		int length = 6;
		for (int i = 0; i < length; i++) {
			int index = rand.nextInt(letters.length());
			password.append(letters.charAt(index));
		}
		return password.toString();
	}

	private String readEmailBody(String filename, User user) {
		StringBuffer sb = new StringBuffer();
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.forEach(line -> {
				line = line.replace("${FNAME}", user.getFName());
				line = line.replace("${LNAME}", user.getLName());
				line = line.replace("${TEMP_PWD}", user.getPwd());
				line = line.replace("${EMAIL}", user.getEmail());
				sb.append(line);

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();

	}
}


