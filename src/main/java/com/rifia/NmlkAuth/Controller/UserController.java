package com.rifia.NmlkAuth.Controller;

import com.rifia.NmlkAuth.Entity.User;
import com.rifia.NmlkAuth.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public void login(@RequestBody User user) {
       authService.login(user);
    }

    @PostMapping("/register")
    public void registration(@RequestBody User user){
        authService.userRegistration(user);
    }

}
