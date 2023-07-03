package com.idosinchuk.tddcompleteguide.infraestructure.persistence.mapper;

import com.idosinchuk.tddcompleteguide.domain.model.Address;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressEntity addressToAddressEntity(Address address);

    Address addressEntityToAddress(AddressEntity addressEntity);

    default Optional<Address> optionalAddressEntityToOptionalAddress(Optional<AddressEntity> addressEntity) {
        return addressEntity.map(this::addressEntityToAddress);
    }
}