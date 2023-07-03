package com.idosinchuk.tddcompleteguide.domain.repository;

import com.idosinchuk.tddcompleteguide.domain.model.Address;

import java.util.Optional;

public interface AddressRepository {
    Address save(Address address);

    Optional<Address> findById(int addressId);
}
