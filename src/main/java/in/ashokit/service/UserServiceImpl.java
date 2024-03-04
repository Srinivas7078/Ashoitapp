package in.ashokit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginFormBinding;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.entity.UserDtls;
import in.ashokit.repo.UserDtlsRepo;
import in.ashokit.utility.EmailUtils;
import in.ashokit.utility.PwdUtilis;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDtlsRepo userRepo;
	@Autowired
	private EmailUtils sendEmail;
	@Autowired
	private HttpSession session;

	@Override
	public String logIn(LoginFormBinding form) {
		UserDtls entity = userRepo.findByEmail(form.getEmail());
		StringBuffer sb=new StringBuffer();
		if (entity == null) {
			return "Invalid Credentials";
		}if(entity.getAccStatus().equals("LOCKED")) {
			return "Your account is locked ";
		}
		if(!entity.getPwd().equals(form.getPwd())) {
			return "Invalid Credetials";
		}
		     session.setAttribute("userId", entity.getUserId());
			return "success";
		}


	@Override
	public Boolean signUp(SignUpForm form) {
		UserDtls user = userRepo.findByEmail(form.getEmail());
		if (user != null) {
			return false;
		}
		UserDtls entity = new UserDtls();
		BeanUtils.copyProperties(form, entity);
		String pwd = PwdUtilis.generatePassword();
		entity.setPwd(pwd);
		entity.setAccStatus("LOCKED");
		userRepo.save(entity);
		String to = form.getEmail();
		StringBuffer body = new StringBuffer("");
		body.append("<h1>Use the below temporary password to unlock the account</h1>");
		body.append("Temporary Pwd:" + pwd);
		body.append("<br/>");
		body.append(
				"<a href=\"http://localhost:8080/unlock?email=" + to + "\"> Click here to unlock your account </a>");
		sendEmail.sendEmail(to, "Temp_Pwd", body.toString());

		return true;
	}

	@Override
	public Boolean unLock(UnlockForm form) {
		UserDtls entity = userRepo.findByEmail(form.getEmail());
		if (form.getTempPwd().equals(entity.getPwd())) {
			entity.setPwd(form.getNewPwd());
			entity.setAccStatus("Unlocked");
			userRepo.save(entity);

		} else {
			return false;
		}
		return true;
	}

	@Override
	public Boolean forGot(String email) {
		UserDtls entity = userRepo.findByEmail(email);
		Boolean status = false;
		if (entity != null) {
			StringBuffer body = new StringBuffer("");
			body.append("Use this password to login to your account");
			body.append("-->" + entity.getPwd());
			sendEmail.sendEmail(email, "Password to unlock your account", body.toString());
			status = true;
//			return true;
		} else {
			status = false;
		}
		return status;
	}

}
