package com.example.class_register_server.controller;

import com.example.class_register_server.auth.JwtUtil;
import com.example.class_register_server.model.User;
import com.example.class_register_server.model.request.LoginReq;
import com.example.class_register_server.model.response.ErrorRes;
import com.example.class_register_server.model.response.LoginRes;
// import com.example.class_register_server.repository.UserRepository;
import com.example.class_register_server.service.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;


    private JwtUtil jwtUtil;
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {

        try {
            Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));

            // Getting user info about who is the User (based on email).
            String email = authentication.getName();
            String domain = getDomainFromEmail(email);

            // If user is null in DB then save it to the DB, depending on email it will save teacher or student user.
            User user = userDetailsService.findByEmail(email);

            if (user == null) {
                if (domain.equals("stud.urz.pl") && loginReq.getStudentIndex() != null) {
                    user = new User(email, "123", false, loginReq.getStudentIndex());
                } else if (domain.equals("urz.pl") && loginReq.getStudentIndex() == null) {
                    user = new User(email,"123", true);
                } else {
                    user = new User(email, "123");
                }
                userDetailsService.saveUser(user);
            }

            // Check if student logs in with proper Index (Setted on first login)
            if (domain.equals("stud.urz.pl")) {
                if (!(user.getStudentIndex().equals(loginReq.getStudentIndex()))) {
                    System.out.println("User logged with bad StudentIndex");
                    ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid student index");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
                } 
            }
            
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(email,token);

            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    /**
     * Method to extract the domain from an email address.
     * 
     * @param email The email address to extract the domain from.
     * @return The domain part of the email address.
    */
    public static String getDomainFromEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        String[] parts = email.split("@");
        return parts[1];
    }
}   