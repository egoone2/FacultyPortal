package ru.osokin.carpetwash.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.osokin.carpetwash.models.Client;
import ru.osokin.carpetwash.services.ClientsService;
import ru.osokin.carpetwash.util.ClientsValidator;

@Controller
@RequestMapping("/clients")
public class ClientsController {
    private final ClientsService clientsService;
    private final ClientsValidator validator;

    @Autowired
    public ClientsController(ClientsService clientsService, ClientsValidator validator) {
        this.clientsService = clientsService;
        this.validator = validator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("clients", clientsService.findAll());
        return "clients/index";
    }

    @GetMapping("/add")
    public String createClient(@ModelAttribute("client") Client client) {
        return "clients/new";
    }

    @PostMapping
    public String addClient(@ModelAttribute("client") @Valid Client client,

                            BindingResult bindingResult) {

        validator.validate(client,bindingResult);

        if (bindingResult.hasErrors())
            return "/clients/new";
        clientsService.save(client);
        return "redirect:/carpets";
    }
}
