package org.test.gesheft.address;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(@Autowired AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public List<Address> getAddressByClient(int owner){
        return addressRepository.findByOwnerId(owner);
    }

    public Address getAddressById(long id) {
        Optional<Address> emp = addressRepository.findById(id);
        return emp.orElse(null);
    }

    public Address saveOrUpdateAddress(Address address){
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

}
