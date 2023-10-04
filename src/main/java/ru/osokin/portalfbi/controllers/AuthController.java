package ru.osokin.portalfbi.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(user);
        return "redirect:/auth/login";
    }
}
