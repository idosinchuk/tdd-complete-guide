package com.idosinchuk.tddcompleteguide.application.service;

import com.idosinchuk.tddcompleteguide.domain.model.User;
import com.idosinchuk.tddcompleteguide.domain.repository.UserRepository;
import com.idosinchuk.tddcompleteguide.domain.service.impl.UserServiceImpl;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testCreateUser() {
        User user = TestDataUtil.createUserMock(1);
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals(user, createdUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById() {
        int userId = 1;
        User user = TestDataUtil.createUserMock(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserById(userId);

        assertEquals(user, retrievedUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserByUsername() {
        String username = "testUser";
        User user = TestDataUtil.createUserMock(1);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserByUsername(username);

        assertEquals(user, retrievedUser);
        verify(userRepository, times(1)).findByUsername(username);
    }
}
