package ru.osokin.carpetwash.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.osokin.carpetwash.models.Client;
import ru.osokin.carpetwash.repositories.ClientsRepository;
import ru.osokin.carpetwash.services.ClientsService;

@Component
public class ClientsValidator implements Validator {

    private final ClientsRepository repository;
    private final ClientsService service;
    @Autowired
    public ClientsValidator(ClientsRepository repository, ClientsService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Client.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
        if (repository.findByPhoneNumber(client.getPhoneNumber()).isPresent())
            service.updateClient(client);
    }
}
