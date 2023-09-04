package ru.osokin.portalfbi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.services.security.UsersService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{id}")
    public String userPage(Model model, @PathVariable("id") Long id) {
        User user = usersService.getById(id).get();
        model.addAttribute("user", user);
        return "auth/user";
    }
}
