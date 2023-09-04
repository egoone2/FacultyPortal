package ru.osokin.portalfbi.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.services.security.RegistrationService;
import ru.osokin.portalfbi.util.UsersValidator;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;

    private final UsersValidator validator;


    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, name = "error") String error) {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("visitor") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("visitor") @Valid User user,
                                      BindingResult bindingResult) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(user);
        return "redirect:/auth/login";
    }
}
