package in.ashokit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.DashBoardResponse;
import in.ashokit.binding.EnquieryForm;
import in.ashokit.service.EnquieryServiceImpl;

@Controller
public class EnquieryController {
	@Autowired
	private HttpSession session;
	@Autowired
	private EnquieryServiceImpl impl;
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/dashboard")
    public String dashBoard(Model model) {
		Integer userId = (Integer)session.getAttribute("userId");
		DashBoardResponse dashBoard = impl.dashBoard(userId);
		model.addAttribute("dashboard",dashBoard);
    	return "dashboard";
    }
	@PostMapping("/Addenquiry")
    public String handleAddEnquiery(@ModelAttribute("form")EnquieryForm form,Model model) {
		Boolean status = impl.saveEnquiry(form);
		if (status) {
			model.addAttribute("succMsg", "Data saved successfully");
		} else {
			model.addAttribute("errMsg", "There is a problem to save your data");
		}
    	return "add-enquiry";
    }
	@GetMapping("/enquiry")
    public String addEnquiery(Model model) {
		model.addAttribute("addAttribute",new EnquieryForm());
		model.addAttribute("courses",impl.getCourses());
		model.addAttribute("status",impl.getStatus());
    	return "add-enquiry";
    }
	
	@GetMapping("/enquires")
    public String viewEnquieries() {
    	return "view-enquiries";
    }
}
