package com.desafio.agregadordeinvestimento.controller;


import com.desafio.agregadordeinvestimento.dto.CreatedUserDto;
import com.desafio.agregadordeinvestimento.entity.User;
import com.desafio.agregadordeinvestimento.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/users")
public class UserController {



    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> createdUser(@RequestBody CreatedUserDto createdUserDto){

        var response = userService.createdUser(createdUserDto);


        return ResponseEntity.created(URI.create("/v1/users/" + response.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){

        var user = userService.getUserById(userId);

        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
