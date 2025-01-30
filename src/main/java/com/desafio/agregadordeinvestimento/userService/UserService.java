package com.desafio.agregadordeinvestimento.userService;

import com.desafio.agregadordeinvestimento.dto.CreatedUserDto;
import com.desafio.agregadordeinvestimento.dto.UpdateUserDto;
import com.desafio.agregadordeinvestimento.entity.User;
import com.desafio.agregadordeinvestimento.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
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

    public Optional<User> getUserById(String userId){

      return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }



    public void updateUser(String userId, UpdateUserDto updateUserDto){
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()){
            var user = userEntity.get();

            if (updateUserDto.username() != null){
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null){
                user.setPassword(updateUserDto.password());
            }
            userRepository.save(user);

        }



    }


    public void delete(String userId){

        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);


        if (userExists){
            userRepository.deleteById(id);
        }

    }
}
