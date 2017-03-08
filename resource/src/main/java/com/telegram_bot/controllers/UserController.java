package com.telegram_bot.controllers;

import com.telegram_bot.entity.Users;
import com.telegram_bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Greg on 9/10/16.
 */

@RestController
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Users> findAllUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/update/state/{idusers}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin(origins = "*", maxAge = 3600,
//            allowedHeaders = { "x-auth-token", "x-requested-with","Content-Type","X-CSRF-Token" },
//            methods = {RequestMethod.POST})
    public List<Users> updateUserState(@PathVariable("idusers") int idusers, @RequestBody Users usersEntity){
        Users update = userRepository.findOne(idusers);
        update.setState(usersEntity.getState());
        userRepository.save(update);
        return userRepository.findAll();
    }

    @RequestMapping(value = "/update/restrictions/{idusers}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Users> updateUserRest(@PathVariable("idusers") int idusers, @RequestBody Users usersEntity){
        Users update = userRepository.findOne(idusers);
        update.setRestricted(usersEntity.getRestricted());
        userRepository.save(update);
        return userRepository.findAll();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Users> addUser(@RequestBody Users usersEntity){
        userRepository.save(usersEntity);
        return userRepository.findAll();
    }

    @RequestMapping(value = "/delete/{idusers}", method = RequestMethod.POST)
    public List<Users> removeUser(@PathVariable("idusers") int idusers){
        userRepository.delete(idusers);
        return userRepository.findAll();
    }

    @RequestMapping(value = "/update/{idusers}", method = RequestMethod.PATCH,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Users> updateUser(@PathVariable("idusers") int idusers, @RequestBody Users usersEntity){
        Users update = userRepository.findOne(idusers);
        update.setUsername(usersEntity.getUsername());
        update.setLastseen(usersEntity.getLastseen());
        update.setState(usersEntity.getState());
        userRepository.save(update);
        return userRepository.findAll();
    }

}
