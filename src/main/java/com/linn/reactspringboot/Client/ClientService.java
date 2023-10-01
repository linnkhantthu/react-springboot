package com.linn.reactspringboot.Client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new IllegalStateException("Client does not exist"));
    }

    public ResponseEntity<Client> createClient(Client client) throws URISyntaxException {
        Optional<Client> clientOptional = clientRepository.findClientByEmail(client.getEmail());
        if (clientOptional.isPresent()) {
            throw new IllegalStateException("User already exist with this email");
        }
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);

    }

    @Transactional
    public ResponseEntity<Client> updateClient(Long id, Client client) {
        Optional<Client> clientOptional = clientRepository.findClientByEmail(client.getEmail());
        if (clientOptional.isPresent()) {
            throw new IllegalStateException("User already exist with this email");
        }
        Client currentClient = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Did not find the client"));
        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());
        currentClient = clientRepository.save(client);
        return ResponseEntity.ok(currentClient);
    }

    public ResponseEntity<Client> deleteClient(Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
