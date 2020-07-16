package com.rifia.NmlkAuth.Service;

import com.rifia.NmlkAuth.Entity.User;
import com.rifia.NmlkAuth.Error.FailedAuthException;
import com.rifia.NmlkAuth.Error.UniqueConditionException;
import com.rifia.NmlkAuth.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void login(User user) {
        try {

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());

            Authentication authentication =
                    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }
        catch(AuthenticationException e){
            throw new FailedAuthException("Failed to authenticate : " + user.getLogin());
        }
    }

    public void userRegistration(User user){

        user.setLogin(user.getLogin());
        user.setPassword(passwordEncoder.encode(user.getPassword()));


            if(this.userRepository.findByLogin(user.getLogin()).isPresent())
                throw new UniqueConditionException("Login " + user.getLogin() + " already exists");
            this.userRepository.save(user);
    }
}
