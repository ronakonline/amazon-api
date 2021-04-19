package com.jay.amazonapi.Services;

import com.jay.amazonapi.Models.User;
import com.jay.amazonapi.Models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
   private UserRepository repository;

    public List<User> getusers(){
        return repository.findAll();
    }

    public String adduser(User user){
        System.out.println(user);
        if(user.getEmail()==null || user.getEmail()==""){
            return "Email Required!";
        }
        else if(user.getPassword()==null || user.getPassword()==""){
            return "Password Required!";
        }else{
            var op = repository.findByEmail(user.getEmail());
            if(op==null) {
                repository.insert(user);
                return null;
            }else{
                return "Email Already Used!";
            }
        }

    }

    public Optional<User> getuser(String id){
        return repository.findById(id);
    }

}
