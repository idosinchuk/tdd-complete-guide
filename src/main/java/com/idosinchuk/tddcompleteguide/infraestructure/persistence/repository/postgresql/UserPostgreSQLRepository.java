package com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.postgresql;

import com.idosinchuk.tddcompleteguide.domain.model.User;
import com.idosinchuk.tddcompleteguide.domain.repository.UserRepository;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.UserEntity;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.mapper.UserMapper;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPostgreSQLRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserPostgreSQLRepository(
            UserJpaRepository userJpaRepository,
            UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity requestEntity = userMapper.userToUserEntity(user);
        UserEntity responseEntity = userJpaRepository.save(requestEntity);
        return userMapper.userEntityToUser(responseEntity);
    }

    @Override
    public Optional<User> findById(int userId) {
        Optional<UserEntity> optionalUserEntity = userJpaRepository.findById(userId);
        return userMapper.optionalUserEntityToOptionalUser(optionalUserEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserEntity> optionalUserEntity = userJpaRepository.findByUsername(username);
        return userMapper.optionalUserEntityToOptionalUser(optionalUserEntity);
    }
}