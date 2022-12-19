package ru.osokin.carpetwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.osokin.carpetwash.models.Client;
import ru.osokin.carpetwash.repositories.ClientsRepository;

@Service
@Transactional(readOnly = true)
public class SaleService {
    private static final int CARPETS_FOR_TEN_PERCENT_SALE = 5;
    private final ClientsRepository clientsRepository;
    @Autowired
    public SaleService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public void updateClientsSale(String phoneNumber, Client client) {
        client.setPhoneNumber(phoneNumber);
        if (client.getCarpets().size() >= CARPETS_FOR_TEN_PERCENT_SALE)
            client.setSale(10);
        clientsRepository.save(client);
    }
}
