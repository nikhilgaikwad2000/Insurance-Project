package in.ashokit.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.AccountBinding;
import in.ashokit.repo.AccountRepo;
import in.ashokit.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo repo;

	@Override
	public String createAccount(AccountBinding accountBinding) {

		accountBinding = repo.save(accountBinding);
		if (accountBinding.getId() != null) {
			return "Account Create Successfully";
		} else {
			return "Account Faild To Save";
		}

	}

	@Override
	public List<AccountBinding> viewAccount() {
            List<AccountBinding> list = repo.findAll();
		return list;
	}

	@Override
	public String updateAccount(AccountBinding accountBinding) {
		if(repo.existsById(accountBinding.getId())) {
			repo.save(accountBinding);
			return "Account Update Success";
		}
		return "No Record Found";
	}

	@Override
	public String deleteAccount(Integer id) {
		repo.deleteById(id);
		return "Accont delete successfully";
	}

	

}
