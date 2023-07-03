package com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.jpa;

import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Integer> {
}