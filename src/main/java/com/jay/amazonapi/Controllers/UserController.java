package com.jay.amazonapi.Controllers;

import com.jay.amazonapi.CustomizedResponse;
import com.jay.amazonapi.Models.User;
import com.jay.amazonapi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    //To get list of all users
    @GetMapping("/user/all")
    public ResponseEntity getusers(){
        return new ResponseEntity(service.getusers(), HttpStatus.OK);
    }


    //To Create New User
    @PostMapping(value = "/user/register",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity adduser(@RequestBody User user)  {
        CustomizedResponse customizedResponse;
        try {
            service.adduser(user);
            customizedResponse = new CustomizedResponse("User Created!",null );
            return new ResponseEntity(customizedResponse,HttpStatus.OK);
        }catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse,HttpStatus.BAD_REQUEST);
        }

    }


    //To get user by userid
    @GetMapping("/user/getuser/{id}")
    public ResponseEntity getuser(@PathVariable("id") String id){
        CustomizedResponse customizedResponse;
        try{
            customizedResponse = new CustomizedResponse("User Found", Collections.singletonList(service.getuser(id)));
            return  new ResponseEntity(customizedResponse,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }


}
