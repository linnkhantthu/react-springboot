package com.linn.reactspringboot.Client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public Client getClient(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new IllegalStateException("Client does not exist"));
    }

    public ResponseEntity createClient(Client client) throws URISyntaxException{
        System.out.println(client);
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);

    }

    public ResponseEntity updateClient(Long id, Client client){
        Client currentClient = clientRepository.findById(id).orElseThrow(()-> new IllegalStateException("Did not find the client"));
        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());
        currentClient = clientRepository.save(client);
        return ResponseEntity.ok(currentClient);
    }

    public ResponseEntity deleteClient(Long id){
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
