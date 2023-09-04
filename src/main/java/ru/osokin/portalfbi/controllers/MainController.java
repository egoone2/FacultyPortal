package ru.osokin.portalfbi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.osokin.portalfbi.repositories.MessagesRepository;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MessagesRepository messagesRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("messages", messagesRepository.findAll());

        return "main";
    }

}
