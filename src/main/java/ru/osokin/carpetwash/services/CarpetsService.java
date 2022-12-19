package ru.osokin.carpetwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.osokin.carpetwash.dto.CarpetDTO;
import ru.osokin.carpetwash.models.Carpet;
import ru.osokin.carpetwash.models.Client;
import ru.osokin.carpetwash.repositories.CarpetsRepository;
import ru.osokin.carpetwash.util.PriceCalculator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarpetsService {
    private final CarpetsRepository carpetsRepository;
    private final ClientsService clientsService;
    private final PriceCalculator calculator;
    @Autowired
    public CarpetsService(CarpetsRepository carpetsRepository, ClientsService clientsService, PriceCalculator calculator) {
        this.carpetsRepository = carpetsRepository;
        this.clientsService = clientsService;
        this.calculator = calculator;
    }

    public Carpet findById(int id) {
        return carpetsRepository.findById(id).orElse(null);
    }

    public List<Carpet> findAll() {
        return carpetsRepository.findAll();
    }

    @Transactional
    public void add(CarpetDTO dto) {
        Carpet carpet = enrichCarpet(dto);
        carpetsRepository.save(carpet);
    }

    private Carpet enrichCarpet(CarpetDTO dto) {
        Carpet carpet = new Carpet();
        Client client = clientsService.save(dto.getClientPhoneNumber(), dto.getClientName());
        carpet.setOwner(client);
        carpet.setLength(dto.getLength());
        carpet.setWidth(dto.getWidth());
        carpet.setWhenTaken(LocalDateTime.now());
        carpet.setArea(carpet.getWidth() * carpet.getLength());
        client.getCarpets().add(carpet);
        calculator.calculate(carpet);
        return carpet;
    }

// TODO: программа лояльности.
}
