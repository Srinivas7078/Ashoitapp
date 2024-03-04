package in.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.LoginFormBinding;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.service.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userImpl;

	@PostMapping("/signup")
	public String handleSignUp(SignUpForm form, Model model) {
		Boolean status = userImpl.signUp(form);
		if (status) {
			model.addAttribute("succMsg", "Check Your Email");
		} else {
			model.addAttribute("errMsg", "Choose Unique EmailId");
		}

		return "signup";
	}

	@PostMapping("/login")
	public String loadLoginPage(@ModelAttribute("loginForm") LoginFormBinding loginForm, Model model) {
		String status = userImpl.logIn(loginForm);
		if(status.contains("success")) {
			return "redirect:/dashboard";
		}
		model.addAttribute("errMsg",status);
		return "login";
	}

	@GetMapping("/login")
	public String handleLoginPage(Model model) {
		model.addAttribute("login", new LoginFormBinding());
		return "login";
	}

	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}

	@GetMapping("/unlock")
	public String unLock(@RequestParam String email, Model model) {
		UnlockForm unlockObj = new UnlockForm();
		unlockObj.setEmail(email);
		model.addAttribute("unlock", unlockObj);
		return "unlock";
	}

	@PostMapping("/unlock")
	public String handleUnlock(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
		if (unlock.getNewPwd().equals(unlock.getConformPwd())) {
			Boolean status = userImpl.unLock(unlock);
			if (status) {
				model.addAttribute("succMsg", "your account unlocked successfully");
			} else {
				model.addAttribute("errMsg", "Please enter valid Temporary msg");
			}
		} else {
			model.addAttribute("errMsg", "New Password and conform password should be same");
		}
		return "unlock";
	}

	@GetMapping("/forgot")
	public String forgotPwdPage(Model model) {
		model.addAttribute("forgot","");
		return "forgotPwd";
	}
	
	@PostMapping("/forgot")
	public String HandleforgotPage(@RequestParam String email,Model model) {
		Boolean status = userImpl.forGot(email);
		if (status) {
			model.addAttribute("succMsg", "Please check your emial for password");
		} else {
			model.addAttribute("errMsg", "Invalid credentials");
		}
		return "forgotPwd";
	}
}
