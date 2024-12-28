package com.desafio.agregadordeinvestimento.controller;


import com.desafio.agregadordeinvestimento.dto.CreatedUserDto;
import com.desafio.agregadordeinvestimento.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity<User> createdUser(@RequestBody CreatedUserDto createdUserDto){

        return null;
    }

    @GetMapping("{/userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){

        return null;
    }
}
