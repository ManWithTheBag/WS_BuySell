package org.buysell.controller.userContr;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.buysell.model.user.User;
import org.buysell.service.userService.UserService;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;

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

    @GetMapping("/login-error")
    @ResponseBody
    public void loginError(Throwable ex){
        String errorMessage = null;

        errorMessage = ex.getMessage();
//        HttpSession session = request.getSession(false);
//
//        AuthenticationException ex = (AuthenticationException) session
//                .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//        if (ex != null) {
//            errorMessage = ex.getMessage();
//        }

        System.out.println(errorMessage);
    }
    @PostMapping("/registration")
    public String register(@ModelAttribute("user") User user){
        userService.createUser(user);
        return "redirect:/";
    }

}
