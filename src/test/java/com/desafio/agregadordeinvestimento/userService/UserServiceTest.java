package com.desafio.agregadordeinvestimento.userService;

import com.desafio.agregadordeinvestimento.dto.CreatedUserDto;
import com.desafio.agregadordeinvestimento.dto.UpdateUserDto;
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
import static org.mockito.Mockito.*;

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
    class getUserById {
        @Test
        @DisplayName("Sould get user by id with success when optional is present")
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


            assertThrows(RuntimeException.class, () -> userService.getUserById(uuid.toString()));

            assertEquals(uuid, uuidArgumentCaptor.getValue());

        }
    }

    @Nested
    class getListUser {
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


    @Nested
    class deleteById {
        @Test
        @DisplayName("Shloud delete user with success when user exists")
        void shouldDeleteWithSuccessWhenUserExist() {


            doReturn(true).when(userRepository).existsById(uuidArgumentCaptor.capture());

            doNothing().when(userRepository).deleteById(uuidArgumentCaptor.capture());


            // act

            var userId = UUID.randomUUID();

            userService.delete(userId.toString());
            // assert

            var idList = uuidArgumentCaptor.getAllValues();
            assertEquals(userId, idList.get(0));
            assertEquals(userId, idList.get(1));


            verify(userRepository, times(1)).existsById(idList.get(0));

            verify(userRepository, times(1)).deleteById(idList.get(1));
        }

        @Test
        @DisplayName("Shloud not delete user when user not exists")
        void shouldNotDeleteUserWhenNotExists() {


            doReturn(false).when(userRepository).existsById(uuidArgumentCaptor.capture());


            // act

            var userId = UUID.randomUUID();

            userService.delete(userId.toString());
            // assert


            assertEquals(userId, uuidArgumentCaptor.getValue());


            verify(userRepository, times(1)).existsById(uuidArgumentCaptor.getValue());


            verify(userRepository, times(0)).deleteById(any());
        }
    }

    @Nested
    class updateUserById {

        @Test
        @DisplayName("Sould update user by id with user exists and username and password is filled")
        void shouldUpdateUserByIdWhenUserExistsAndUsernameAndPasswordIsFilled() {

            // arrange

            var updateDto = new UpdateUserDto("newusername", "newpassword");

            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());


            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());

            // act

           userService.updateUser(user.getUserId().toString(),updateDto);
            // assert


            assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(updateDto.username(), userCaptured.getUsername());
            assertEquals(updateDto.password(), userCaptured.getPassword());

            verify(userRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(userRepository, times(1)).save(user);
        }
        @Test
        @DisplayName("Sould not update user when user not exists")
        void shouldNotUpdateUserWhenUserNotExists() {

            // arrange

            var updateDto = new UpdateUserDto("newusername", "newpassword");


            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());

            var userId = UUID.randomUUID();

            // act

           userService.updateUser(userId.toString(),updateDto);
            // assert


            assertEquals(userId, uuidArgumentCaptor.getValue());


            verify(userRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(userRepository, times(0)).save(any());
        }
    }

}