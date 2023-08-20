package org.buysell;

import org.buysell.controller.productContr.ProductController;
import org.buysell.model.enums.Role;
import org.buysell.model.user.User;
import org.buysell.model.user.UserRole;
import org.buysell.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "org.buysell")
public class BuysellApplication {
//    @Autowired
//    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BuysellApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProductController productController){

//        User user = new User();
//        List<UserRole> userRoleList = new ArrayList<>();
//        UserRole userRole = new UserRole();
//        userRole.setRole(Role.ROLE_USER.name());
//        userRole.setUser(user);
//        userRoleList.add(userRole);
//        user.setName("Petter");
//        user.setEmail("qwe@gmail.com");
//        user.setPassword("123");
//        user.setUserImage(null);
//        user.setPhoneNumber("123");
//        user.setActive(true);
//        user.setDataOfCreate(LocalDateTime.now());
//        user.setRoleList(userRoleList);
//
//        userService.createUser(user);
        return runner ->{
            System.out.println("OK");
        };
    }
}
