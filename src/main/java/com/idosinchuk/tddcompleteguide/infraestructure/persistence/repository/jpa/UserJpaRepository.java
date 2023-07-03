package com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.jpa;

import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
}