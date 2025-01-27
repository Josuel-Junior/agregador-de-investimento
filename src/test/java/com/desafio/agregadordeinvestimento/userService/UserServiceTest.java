package com.desafio.agregadordeinvestimento.userService;

import com.desafio.agregadordeinvestimento.dto.CreatedUserDto;
import com.desafio.agregadordeinvestimento.entity.User;
import com.desafio.agregadordeinvestimento.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks


    private UserService userService;

    @Nested
    class createdUser {
        @Test
        @DisplayName("Should created a user with success")
        void shouldCreateUser() {

            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(user).when(userRepository).save(any());
            var input = new CreatedUserDto("josuel", "josuel@teste.com", "123456");


            var output = userService.createdUser(input);

            assertNotNull(output);
        }
    }

}