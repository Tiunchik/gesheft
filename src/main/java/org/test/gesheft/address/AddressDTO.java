package org.test.gesheft.address;

import org.springframework.stereotype.Component;
import org.test.gesheft.clients.ClientDTO;

@Component
public class AddressDTO {

    private long id;

    private String name;

    private ClientDTO owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientDTO getOwner() {
        return owner;
    }

    public void setOwner(ClientDTO owner) {
        this.owner = owner;
    }
}
