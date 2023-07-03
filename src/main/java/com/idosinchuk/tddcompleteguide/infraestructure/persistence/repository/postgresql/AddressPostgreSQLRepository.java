package com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.postgresql;

import com.idosinchuk.tddcompleteguide.domain.model.Address;
import com.idosinchuk.tddcompleteguide.domain.repository.AddressRepository;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.AddressEntity;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.mapper.AddressMapper;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.jpa.AddressJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressPostgreSQLRepository implements AddressRepository {

    private final AddressJpaRepository addressJpaRepository;

    private final AddressMapper addressMapper;

    @Autowired
    public AddressPostgreSQLRepository(
            AddressJpaRepository addressJpaRepository,
            AddressMapper addressMapper) {
        this.addressJpaRepository = addressJpaRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public Address save(Address address) {
        AddressEntity addressEntity = addressMapper.addressToAddressEntity(address);
        addressJpaRepository.save(addressEntity);
        return address;
    }

    @Override
    public Optional<Address> findById(int addressId) {
        Optional<AddressEntity> addressEntity = addressJpaRepository.findById(addressId);
        return addressMapper.optionalAddressEntityToOptionalAddress(addressEntity);
    }
}