package com.example.AutoExpress.services;

import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.repositories.RoleRepository;
import com.example.AutoExpress.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserServiceTest {


    private UserEntity user;

    private UserRepository mockedUserRepository;

    private UserService userService;
    private UserService mockedUserService;


    @Before
    public void init() {
        this.user = new UserEntity();

        user.setId(2);
        user.setUsername("Gosho");
        user.setEmail("gosho@gmail");

        this.mockedUserService = Mockito.mock(UserService.class);

        this.mockedUserRepository = Mockito.mock(UserRepository.class);
        RoleRepository roleRepository = Mockito.mock(RoleRepository.class);

        ModelMapper modelMapper = new ModelMapper();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthService authService = new AuthService(mockedUserRepository);

        UserService userService = new UserService(passwordEncoder, mockedUserRepository, roleRepository, modelMapper, authService);
    }

    @Test
    public void getByUsernameShouldReturnCorrect() {

        Mockito.when(this.mockedUserRepository.findByUsername("Gosho")).thenReturn(Optional.ofNullable(this.user));


        UserEntity expected = this.user;

        UserEntity actualUser = userService.getUserByUserName("Gosho");

        Assertions.assertEquals(expected.getUsername(), actualUser.getUsername());
        Assertions.assertEquals(expected.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(expected.getId(), actualUser.getId());
    }

    @Test
    public void isUsernameUniqueShouldReturnTrue() {
        Mockito.when(this.mockedUserService.isUsernameUnique("Gosho")).thenReturn(true);

        boolean expectedResult = false;

        boolean actualResult = userService.isUsernameUnique("Gosho");


        Assertions.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void isEmailUniqueShouldReturnTrue() {}


}
