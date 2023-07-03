package com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.postgresql;

import com.idosinchuk.tddcompleteguide.domain.model.User;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.UserEntity;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.mapper.UserMapper;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.jpa.UserJpaRepository;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserPostgreSQLRepositoryTest {

    private UserPostgreSQLRepository repository;

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new UserPostgreSQLRepository(userJpaRepository, userMapper);
    }

    @Test
    void testSaveUser() {
        int userId = 1;

        // Create a sample user object
        User user = TestDataUtil.createUserMock(userId);

        // Create a sample user entity
        UserEntity userEntity = TestDataUtil.createUserEntityMock(userId);

        // Mock the user mapper
        when(userMapper.userToUserEntity(user)).thenReturn(userEntity);

        // Mock the user JPA repository save method
        when(userJpaRepository.save(userEntity)).thenReturn(userEntity);

        // Mock the user mapper conversion from entity to user
        when(userMapper.userEntityToUser(userEntity)).thenReturn(user);

        // Call the save method
        User result = repository.save(user);

        // Verify that the user mapper was called with the correct argument
        verify(userMapper, times(1)).userToUserEntity(user);

        // Verify that the user JPA repository save method was called with the correct argument
        verify(userJpaRepository, times(1)).save(userEntity);

        // Verify that the user mapper conversion from entity to user was called
        verify(userMapper, times(1)).userEntityToUser(userEntity);

        // Assert that the result is the same as the input
        assertEquals(user, result);
    }

    @Test
    void testFindById() {
        int userId = 1;

        // Create a sample user object
        var user = Optional.of(TestDataUtil.createUserMock(userId));

        // Create a sample user entity
        UserEntity userEntity = TestDataUtil.createUserEntityMock(userId);

        // Mock the user JPA repository findById method
        Optional<UserEntity> optionalUserEntity = Optional.of(userEntity);
        when(userJpaRepository.findById(userId)).thenReturn(optionalUserEntity);

        // Mock the user mapper conversion from optional entity to optional user
        when(userMapper.optionalUserEntityToOptionalUser(optionalUserEntity)).thenReturn(user);

        // Call the findById method
        Optional<User> result = repository.findById(userId);

        // Verify that the user JPA repository findById method was called with the correct argument
        verify(userJpaRepository, times(1)).findById(userId);

        // Verify that the user mapper conversion from optional entity to optional user was called
        verify(userMapper, times(1)).optionalUserEntityToOptionalUser(optionalUserEntity);

        // Assert that the result is the same as the optional user converted by the mapper
        assertEquals(user, result);
    }

    @Test
    void testFindByUsername() {
        int userId = 1;
        String username = "testUser";

        // Create a sample user object
        var user = Optional.of(TestDataUtil.createUserMock(userId));

        // Create a sample user entity
        UserEntity userEntity = TestDataUtil.createUserEntityMock(1);

        // Mock the user JPA repository findByUsername method
        Optional<UserEntity> optionalUserEntity = Optional.of(userEntity);
        when(userJpaRepository.findByUsername(username)).thenReturn(optionalUserEntity);

        // Mock the user mapper conversion from optional entity to optional user
        when(userMapper.optionalUserEntityToOptionalUser(optionalUserEntity)).thenReturn(user);

        // Call the findByUsername method
        Optional<User> result = repository.findByUsername(username);

        // Verify that the user JPA repository findByUsername method was called with the correct argument
        verify(userJpaRepository, times(1)).findByUsername(username);

        // Verify that the user mapper conversion from optional entity to optional user was called
        verify(userMapper, times(1)).optionalUserEntityToOptionalUser(optionalUserEntity);

        // Assert that the result is the same as the optional user converted by the mapper
        assertEquals(user, result);
    }
}