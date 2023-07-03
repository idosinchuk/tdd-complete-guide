package com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.postgresql;

import com.idosinchuk.tddcompleteguide.domain.model.Address;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.AddressEntity;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.mapper.AddressMapper;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.jpa.AddressJpaRepository;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddressPostgreSQLRepositoryTest {

    private AddressPostgreSQLRepository repository;

    @Mock
    private AddressJpaRepository addressJpaRepository;

    @Mock
    private AddressMapper addressMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new AddressPostgreSQLRepository(addressJpaRepository, addressMapper);
    }

    @Test
    void testSaveAddress() {
        int addressId = 1;

        // Create a sample address object
        Address address = TestDataUtil.createAddressMock(addressId);

        // Mock the address mapper
        AddressEntity addressEntity = TestDataUtil.createAddressEntityMock(addressId);
        when(addressMapper.addressToAddressEntity(address)).thenReturn(addressEntity);

        // Mock the address JPA repository save method
        when(addressJpaRepository.save(addressEntity)).thenReturn(addressEntity);

        // Call the save method
        Address result = repository.save(address);

        // Verify that the address mapper was called with the correct argument
        verify(addressMapper, times(1)).addressToAddressEntity(address);

        // Verify that the address JPA repository save method was called with the correct argument
        verify(addressJpaRepository, times(1)).save(addressEntity);

        // Assert that the result is the same as the input
        assertEquals(address, result);
    }

    @Test
    void testFindById() {
        int addressId = 1;

        // Create a sample address entity
        AddressEntity addressEntity = TestDataUtil.createAddressEntityMock(addressId);

        // Mock the address JPA repository findById method
        Optional<AddressEntity> optionalAddressEntity = Optional.of(addressEntity);
        when(addressJpaRepository.findById(addressId)).thenReturn(optionalAddressEntity);

        // Call the findById method
        Optional<Address> result = repository.findById(addressId);

        // Verify that the address JPA repository findById method was called with the correct argument
        verify(addressJpaRepository, times(1)).findById(addressId);

        // Assert that the result is the same as the optional address entity converted by the mapper
        assertEquals(optionalAddressEntity.map(addressMapper::addressEntityToAddress), result);
    }
}