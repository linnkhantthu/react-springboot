package com.linn.reactspringboot.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {
    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();
    }

    @GetMapping(path = "/{id}")
    public Client getClient(@PathVariable Long id){
        return clientService.getClient(id);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody Client client) throws URISyntaxException{
        return clientService.createClient(client);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Client client){
        return clientService.updateClient(id, client);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id){
        return clientService.deleteClient(id);
    }
}
