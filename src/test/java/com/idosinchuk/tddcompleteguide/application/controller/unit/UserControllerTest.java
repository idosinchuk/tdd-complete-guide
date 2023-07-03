package com.idosinchuk.tddcompleteguide.application.controller.unit;

import com.idosinchuk.tddcompleteguide.application.controller.UserController;
import com.idosinchuk.tddcompleteguide.application.service.UserService;
import com.idosinchuk.tddcompleteguide.domain.model.User;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void testCreateUser() {
        User user = TestDataUtil.createUserMock(1);
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).createUser(user);
    }

    @Test
    void testGetUserById() {
        int userId = 1;
        User user = TestDataUtil.createUserMock(userId);
        when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testGetUserByUsername() {
        String username = "testUser";
        User user = TestDataUtil.createUserMock(1);
        when(userService.getUserByUsername(username)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserByUsername(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserByUsername(username);
    }
}
