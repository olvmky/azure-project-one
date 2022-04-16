package com.example.projectOne.Controller;

import com.example.projectOne.DTO.LoginRequest;
import com.example.projectOne.DTO.LoginResponse;
import com.example.projectOne.DTO.RegisterRequest;
import com.example.projectOne.models.UserLogin;
import com.example.projectOne.services.LoginService;
import com.example.projectOne.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;

    private final JWTUtils jwtUtils;

    private final UserDetailsService userDetailsService;

    private final LoginService loginService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTUtils jwtUtils,
                          UserDetailsService userDetailsService, LoginService loginService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.loginService = loginService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String token = jwtUtils.generateToken(userDetails);

        System.out.println(token + " ergneiprngi5rengt");

        return ResponseEntity.ok(new LoginResponse(false, token, null));
    }

    private void authenticate(@NonNull String username, @NonNull String password) throws Exception {
        System.out.println("Authentcating " + username);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException de) {
            System.out.println("User not active");
        } catch (BadCredentialsException be) {
            throw new Exception("Invalid credentials", be);
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody RegisterRequest registerRequest) throws URISyntaxException {
        UserLogin user = loginService.newUser(registerRequest);

        if(user != null) {
            return ResponseEntity.created(new URI("http://localhost:8888/users/" + user.getId())).build();
        }

        return ResponseEntity.internalServerError().build();
    }
}

