package ru.osokin.carpetwash.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.osokin.carpetwash.dto.CarpetDTO;
import ru.osokin.carpetwash.models.Carpet;
import ru.osokin.carpetwash.models.Client;
import ru.osokin.carpetwash.services.CarpetsService;

@Controller
@RequestMapping("/carpets")
public class CarpetsController {

    private final CarpetsService carpetsService;
    @Autowired
    public CarpetsController(CarpetsService carpetsService) {
        this.carpetsService = carpetsService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("carpets", carpetsService.findAll());
        return "carpets/index";
    }
    @GetMapping("/add")
    public String newCarpet(@ModelAttribute("carpet") CarpetDTO dto) {
        return "carpets/new";
    }

    @PostMapping
    public String addCarpet(@ModelAttribute("carpet") @Valid CarpetDTO dto,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "carpets/new";
        carpetsService.add(dto);
        return "redirect:/carpets";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("client") Client client) {
        Carpet carpet = carpetsService.findById(id);
        model.addAttribute("carpet", carpet);
        model.addAttribute("owner", carpet.getOwner());
        return "/carpets/show";
    }
}
