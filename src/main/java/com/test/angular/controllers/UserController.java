package com.test.angular.controllers;

import com.test.angular.repositories.UserRepository;
import com.test.angular.models.TestUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        //List<TestUser> response = (List<TestUser>)userRepository.findAll();
        TestUser newuser = new TestUser();
        //logger.info("hello I am a logger", response);
        model.addAttribute("userinfo",newuser);
        return "users/user";
    }
    @GetMapping("/users/{id}")
    public Optional<TestUser> getUserById(@PathVariable("id") long id){
        Optional<TestUser> response = userRepository.findById(id);
        return response;
    }

    @PostMapping("/users")
    public String postUsers(@ModelAttribute("user") TestUser user,Model model){
        userRepository.save(user);
        model.addAttribute(user);
        return "users/success";
    }
    @GetMapping(path = "/users/edit/{id}")
    public String getEditForm(@PathVariable("id") long id,Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "users/edit-user";
    }


    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable("id") long id, @RequestBody TestUser testUser){
        Optional<TestUser> userData = userRepository.findById(id);
        TestUser _user = userData.get();
        _user.setName(testUser.getName());
        _user.setEmail(testUser.getEmail());
        userRepository.save(_user);
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userRepository.deleteById(id);
        return "users/user";
    }

    @DeleteMapping("/users")
    public String deleteAllUsers(){
        userRepository.deleteAll();
        return "all users deleted!";
    }

}
