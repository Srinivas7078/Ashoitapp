package in.ashokit.service;

import in.ashokit.binding.LoginFormBinding;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;

public interface UserService {
	public String logIn(LoginFormBinding form);

	public Boolean signUp(SignUpForm form);

	public Boolean unLock(UnlockForm form);

	public Boolean forGot(String email);

}
