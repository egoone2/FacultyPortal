package ru.osokin.portalfbi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.osokin.portalfbi.services.MessageService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MessageService messageService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("messages", messageService.getAll());

        return "index";
    }

}
