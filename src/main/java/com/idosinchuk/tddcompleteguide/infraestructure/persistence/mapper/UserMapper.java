package com.idosinchuk.tddcompleteguide.infraestructure.persistence.mapper;

import com.idosinchuk.tddcompleteguide.domain.model.User;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity userToUserEntity(User user);

    User userEntityToUser(UserEntity userEntity);

    default Optional<User> optionalUserEntityToOptionalUser(Optional<UserEntity> userEntity) {
        return userEntity.map(this::userEntityToUser);
    }
}