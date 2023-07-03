package com.idosinchuk.tddcompleteguide.domain.repository;

import com.idosinchuk.tddcompleteguide.domain.model.User;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = TestDataUtil.createUserMock(1);
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userRepository.save(user);

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindById() {
        int userId = 1;
        User user = TestDataUtil.createUserMock(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userRepository.findById(userId);

        assertEquals(Optional.of(user), retrievedUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testFindByUsername() {
        String username = "testUser";
        User user = TestDataUtil.createUserMock(1);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userRepository.findByUsername(username);

        assertEquals(Optional.of(user), retrievedUser);
        verify(userRepository, times(1)).findByUsername(username);
    }
}