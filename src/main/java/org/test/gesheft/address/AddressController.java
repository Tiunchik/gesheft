package org.test.gesheft.address;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    private final ModelMapper mapper;

    public AddressController(@Autowired AddressService addressService,
                             @Autowired ModelMapper mapper) {
        this.addressService = addressService;
        this.mapper = mapper;
    }

    @GetMapping("/address")
    public ResponseEntity<List<AddressDTO>> getAddresses(@RequestParam(value = "client",
            required = false) Integer clientId) {
        List<AddressDTO> list;
        if (clientId != null) {
            list = addressService
                    .getAddressByClient(clientId)
                    .stream()
                    .map(e -> mapper.map(e, AddressDTO.class))
                    .collect(Collectors.toList());
        } else {
            list = addressService
                    .getAllAddress()
                    .stream()
                    .map(e -> mapper.map(e, AddressDTO.class))
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable long id) {
        Address response = addressService.getAddressById(id);
        if (response != null) {
            return new ResponseEntity<>(mapper.map(response, AddressDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO) {
        if (addressDTO.getId() != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Address response = addressService.saveOrUpdateAddress(mapper.map(addressDTO, Address.class));
        return new ResponseEntity<>(mapper.map(response, AddressDTO.class), HttpStatus.OK);
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) {
        if (addressDTO.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Address response = addressService.saveOrUpdateAddress(mapper.map(addressDTO, Address.class));
        return new ResponseEntity<>(mapper.map(response, AddressDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

}
