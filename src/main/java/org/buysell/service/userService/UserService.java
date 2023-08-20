package org.buysell.service.userService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.buysell.model.enums.Role;
import org.buysell.model.user.User;
import org.buysell.model.user.UserRole;
import org.buysell.repository.userRepo.UserRepository;

import org.buysell.repository.userRepo.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    public boolean createUser(User user){
        if(userRepository.findByEmail(user.getEmail()) != null) return false;
        user.setActive(true);
        User savedUser = userRepository.save(user);
        user.setPassword(passwordEncoder().encode(user.getPassword()));

        UserRole userRole = new UserRole();
        userRole.setRole(Role.ROLE_USER.name());
        userRole.setUser(savedUser);
        saveUserRole(userRole);

        System.out.println("User registered with email:" + user.getEmail());
        return true;
    }

    public UserRole saveUserRole(UserRole userRole){
        return userRoleRepository.save(userRole);
    }


    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
