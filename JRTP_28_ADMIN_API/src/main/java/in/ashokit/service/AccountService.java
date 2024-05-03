package in.ashokit.service;

import java.util.List;



import in.ashokit.binding.AccountBinding;

public interface AccountService {
   public String createAccount(AccountBinding accountBinding);
   public List<AccountBinding> viewAccount();
   public String updateAccount(AccountBinding accountBinding);
   public String deleteAccount(Integer id);
}
