package org.test.gesheft.clients;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(@Autowired ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }

    public List<Client> getFilteredClients(String param){
        StringBuilder builder = new StringBuilder("%")
                .append(param)
                .append("%");
        return clientRepository.filter(builder.toString());
    }

    public Client getClientById(int id) {
        Optional<Client> emp = clientRepository.findById(id);
        return emp.orElse(null);
    }

    public Client saveOrUpdateClients(Client client){
        return clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

}
