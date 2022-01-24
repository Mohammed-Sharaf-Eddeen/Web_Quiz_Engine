package com.example.web_quiz_engine.presentationLayer;

import com.example.web_quiz_engine.businessLayer.users.User;
import com.example.web_quiz_engine.businessLayer.users.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/api/register")
    public void addNewUser(@Valid @RequestBody User user) {
        userDetailsService.addNewUser(user);
    }

}
