package com.digiplan.controllers;

import com.digiplan.entities.Address;
import com.digiplan.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return buildResponse(addresses, HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Map<String, Object>> getAddressById(@PathVariable Long addressId) {
        Address address = addressService.getAddressById(addressId);
        return buildResponse(address, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getAddressesByUserId(@PathVariable String userId) {
        List<Address> addresses = addressService.getAddressesByUserId(userId);
        return buildResponse(addresses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveAddress(@RequestBody Address address) {
        Address savedAddress = addressService.saveAddress(address);
        return buildResponse(savedAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Map<String, Object>> updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
        Address updatedAddress = addressService.updateAddress(addressId, address);
        return buildResponse(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Map<String, Object>> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return buildResponse("Address deleted successfully", HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(Object data, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("message", status.getReasonPhrase());
        response.put("data", data);
        return new ResponseEntity<>(response, status);
    }
}
