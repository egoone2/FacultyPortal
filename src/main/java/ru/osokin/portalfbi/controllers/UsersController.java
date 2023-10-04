package ru.osokin.portalfbi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.osokin.portalfbi.models.Message;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.services.UsersService;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{id}")
    public String userPage(Model model, @PathVariable("id") Long id) {
        User user = usersService.getById(id).get();
        Set<Message> messages = user.getMessages();

        model.addAttribute("user", user);
        model.addAttribute("messages", messages);
        return "auth/user";
    }
}
