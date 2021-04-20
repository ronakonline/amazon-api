package com.jay.amazonapi.Controllers;

import com.jay.amazonapi.CustomizedResponse;
import com.jay.amazonapi.Models.User;
import com.jay.amazonapi.Services.UserService;
import com.jay.amazonapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userDetailsService;

    @PostMapping(value = "/auth",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody User user)  {
        CustomizedResponse customizedResponse;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

        }catch (BadCredentialsException e){
            customizedResponse = new CustomizedResponse("Invalid Email or Password!",null);
            return new ResponseEntity(customizedResponse,HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }
}
