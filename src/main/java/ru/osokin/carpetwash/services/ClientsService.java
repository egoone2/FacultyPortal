package ru.osokin.carpetwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.osokin.carpetwash.models.Client;
import ru.osokin.carpetwash.repositories.ClientsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientsService {
    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Transactional
    public void save(Client client) {
        client.setSale(0);
        clientsRepository.save(client);
    }

    @Transactional
    public Client save(String phoneNumber, String name) {
        Optional<Client> optionalClient = clientsRepository.findByPhoneNumber(phoneNumber);
        Client client;

        if (optionalClient.isPresent()) {
            client = optionalClient.get();
            client.setName(name);
        }
        else client = new Client(phoneNumber, name);


        clientsRepository.save(client);
        return client;
    }



    @Transactional
    public void updateClient(Client client) {
        clientsRepository.save(client);
    }



    public List<Client> findAll() {
        return clientsRepository.findAll();
    }
}
