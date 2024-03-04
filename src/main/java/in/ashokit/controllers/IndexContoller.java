package in.ashokit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexContoller {
	@GetMapping("/")
    public String loadIndex() {
    	return "index";
    }
}
