package com.desafio.agregadordeinvestimento.userService;

import com.desafio.agregadordeinvestimento.dto.CreatedUserDto;
import com.desafio.agregadordeinvestimento.entity.User;
import com.desafio.agregadordeinvestimento.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@Service
public class UserService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createdUser(CreatedUserDto createdUserDto){

        var entity = new User(
                null,
                createdUserDto.username(),
                createdUserDto.email(),
                createdUserDto.password(),
                Instant.now(),
                null);

         var userSaved = userRepository.save(entity);


         return  userSaved.getUserId();

    }
}
