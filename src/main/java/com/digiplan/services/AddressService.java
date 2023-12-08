package com.digiplan.services;

import com.digiplan.entities.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddresses();
    Address getAddressById(Long addressId);
    List<Address> getAddressesByUserId(String userId);
    Address saveAddress(Address address);
    Address updateAddress(Long addressId, Address address);
    void deleteAddress(Long addressId);
}
