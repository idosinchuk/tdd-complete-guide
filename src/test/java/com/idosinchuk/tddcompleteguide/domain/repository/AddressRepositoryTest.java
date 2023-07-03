package com.idosinchuk.tddcompleteguide.domain.repository;

import com.idosinchuk.tddcompleteguide.domain.model.Address;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddressRepositoryTest {

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAddress() {
        Address address = TestDataUtil.createAddressMock(1);
        when(addressRepository.save(address)).thenReturn(address);

        Address savedAddress = addressRepository.save(address);

        assertEquals(address, savedAddress);
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void testFindById_AddressExists() {
        int addressId = 1;
        Address address = TestDataUtil.createAddressMock(addressId);
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        Optional<Address> retrievedAddress = addressRepository.findById(addressId);

        assertEquals(Optional.of(address), retrievedAddress);
        verify(addressRepository, times(1)).findById(addressId);
    }

    @Test
    void testFindById_AddressDoesNotExist() {
        int addressId = 1;
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        Optional<Address> retrievedAddress = addressRepository.findById(addressId);

        assertEquals(Optional.empty(), retrievedAddress);
        verify(addressRepository, times(1)).findById(addressId);
    }
}