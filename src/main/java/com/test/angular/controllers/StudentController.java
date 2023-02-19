package com.test.angular.controllers;

import com.test.angular.models.TestUser;
import com.test.angular.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserRepository userRepository;

    public StudentController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/students")
    public List<TestUser> getUsers(Model model){
        List<TestUser> response = (List<TestUser>)userRepository.findAll();
        logger.info("hello I am a logger", response);
        return response;
    }
    @GetMapping("/students/{id}")
    public Optional<TestUser> getUserById(@PathVariable("id") Long id){
        Optional<TestUser> response = userRepository.findById(id);
        return response;
    }

    @PostMapping("/students")
    public void postUsers(@ModelAttribute("user") TestUser user){
        userRepository.save(user);
    }

    @PutMapping("/students/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody TestUser testUser){
        Optional<TestUser> userData = userRepository.findById(id);
        TestUser _user = userData.get();
        _user.setName(testUser.getName());
        _user.setEmail(testUser.getEmail());
        userRepository.save(_user);
    }

    @DeleteMapping("students/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "users";
    }

    @DeleteMapping("/students")
    public String deleteAllUsers(){
        userRepository.deleteAll();
        return "all users deleted!";
    }

}
