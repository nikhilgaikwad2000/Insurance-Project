package in.ashokit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.AccountBinding;
import in.ashokit.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;

	@PostMapping("/saveAcc")
	public ResponseEntity<String> saveAcc(@RequestBody AccountBinding accountBinding) {
		String account = service.createAccount(accountBinding);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@GetMapping("/viewAcc")
	public ResponseEntity<List<AccountBinding>> viewAcc() {
		List<AccountBinding> list = service.viewAccount();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PutMapping("/updateAcc")
	public ResponseEntity<String> updateAcc(@RequestBody AccountBinding accountBinding) {
		String updateAccount = service.updateAccount(accountBinding);
		return new ResponseEntity<>(updateAccount, HttpStatus.OK);

	}
	@DeleteMapping("/deleteAcc")
	public ResponseEntity<String> deleteAcc(@PathVariable Integer id){
		String deleteAccount = service.deleteAccount(id);
		return new ResponseEntity<>(deleteAccount,HttpStatus.OK);
	}

}
