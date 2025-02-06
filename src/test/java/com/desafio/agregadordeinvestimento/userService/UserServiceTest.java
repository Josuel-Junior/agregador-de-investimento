package com.desafio.agregadordeinvestimento.userService;

import com.desafio.agregadordeinvestimento.dto.CreatedUserDto;
import com.desafio.agregadordeinvestimento.entity.User;
import com.desafio.agregadordeinvestimento.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks


    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

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
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
            var input = new CreatedUserDto("josuel", "josuel@teste.com", "123456");


            var output = userService.createdUser(input);

            assertNotNull(output);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());


        }

        @Test
        void shouldThrowExceptionWhenErrorOcurrs() {


            doThrow(new RuntimeException()).when(userRepository).save(any());
            var input = new CreatedUserDto("josuel", "josuel@teste.com", "123456");



            assertThrows(RuntimeException.class, () -> userService.createdUser(input));

        }
    }

    @Nested
    class getUserById{
        @Test
        @DisplayName("Sould get by id with success when optional is present")
        void shouldGetUserByIdWithSuccessWhenOptionalIsPresent() {

            // arrange


            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());


            // act

            var output = userService.getUserById(user.getUserId().toString());
            // assert

            assertTrue(output.isPresent());

            assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("Sould get by id with success when optional is empty")
        void shouldGetUserByIdWithSuccessWhenOptionalIsEmpty() {

            // arrange


            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );

            var uuid = UUID.randomUUID();
            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());


            // act

            var output = userService.getUserById(uuid.toString());
            // assert

            assertTrue(output.isEmpty());

            assertEquals(uuid, uuidArgumentCaptor.getValue());
        }

        @Test
        void shouldThrowExceptionWhenError() {

            var uuid = UUID.randomUUID();

            doThrow(new RuntimeException()).when(userRepository).findById(uuidArgumentCaptor.capture());


           assertThrows(RuntimeException.class, ()-> userService.getUserById(uuid.toString()));

            assertEquals(uuid, uuidArgumentCaptor.getValue());

        }
    }

    @Nested
    class getListUser{
        @Test
        @DisplayName("shloud get listUser")
        void shouldGetLisUser() {

            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );

            var userList = List.of(user);

            doReturn(userList).when(userRepository).findAll();

            var outPut = userService.listUsers();

            assertNotNull(outPut);

            assertEquals(userList.size(), outPut.size());



        }
    }

}