package com.example.projectOne.services;

import com.example.projectOne.DAO.LoginRepository;
import com.example.projectOne.DTO.RegisterRequest;
import com.example.projectOne.models.Roles;
import com.example.projectOne.models.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    PasswordEncoder passwordEncoder;

    LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserLogin newUser(RegisterRequest registerRequest){
        UserLogin user = new UserLogin(registerRequest.getUsername(),
                registerRequest.getFirstName(), registerRequest.getLastName(),
                passwordEncoder.encode(registerRequest.getPassword()), Roles.EMPLOYEE, registerRequest.getEmail());

        return loginRepository.save(user);
    }
}
