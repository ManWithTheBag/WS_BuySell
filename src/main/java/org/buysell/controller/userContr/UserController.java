package org.buysell.controller.userContr;

import lombok.AllArgsConstructor;
import org.buysell.model.user.User;
import org.buysell.service.userService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "user/registration";
    }

    @GetMapping("/login")
    public String showLoginForm(){
    return "user/customLogin";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("user") User user){
        userService.createUser(user);
        return "redirect:/";
    }

}
