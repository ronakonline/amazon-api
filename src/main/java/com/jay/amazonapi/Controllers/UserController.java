package com.jay.amazonapi.Controllers;

import com.jay.amazonapi.Models.User;
import com.jay.amazonapi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user/all")
    public ResponseEntity getusers(){
        return new ResponseEntity(service.getusers(), HttpStatus.OK);
    }

    @PostMapping(value = "/user/register",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity adduser(@RequestBody User user){
            var msg = service.adduser(user);
            if(msg!=null){
                return  new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
            }else{
                return  new ResponseEntity(HttpStatus.OK);
            }
    }

    @GetMapping("/user/getuser/{id}")
    public ResponseEntity getuser(@PathVariable("id") String id){
        var op = service.getuser(id);
        if(op.isEmpty()){
            return new ResponseEntity("Invalid User id",HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity(op,HttpStatus.OK);
        }
    }


}
