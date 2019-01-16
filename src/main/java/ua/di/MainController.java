package ua.di;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.di.model.User;

@Controller
public class MainController {

	static List<User> users = new ArrayList<>();

	@GetMapping("/view/{name}")

	public String view(@PathVariable("name") String name, Model model) {
		model.addAttribute("msg", "Hello " + name);
		return "index";
	}

	@GetMapping("/raw")
	@ResponseBody
	public String raw() {
		return "Raw data";
	}

	@GetMapping("/users")
	public String getUsers(Model model) {
		model.addAttribute("users", users);
		return "/users";
	}

	@GetMapping("/users/new")
	public String getSingUp() {
		return "/sign_up";
	}

	@PostMapping("/users/new")
//	public String singUp(@ModelAttribute User user) {
//		users.add(user);
//		return "redirect:/users";

	public String singUp(@RequestParam("name") String name, @RequestParam("url") String url) {
		users.add(new User(name, url));
		return "redirect:/users";
	}
}
