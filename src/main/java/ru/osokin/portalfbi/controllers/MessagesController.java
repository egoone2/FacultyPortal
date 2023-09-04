package ru.osokin.portalfbi.controllers;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.osokin.portalfbi.models.Message;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.repositories.MessagesRepository;
import ru.osokin.portalfbi.repositories.UsersRepository;
import ru.osokin.portalfbi.security.UserDetailsImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mes")
public class MessagesController {

    private final MessagesRepository messagesRepository;
    private final UsersRepository usersRepository;

    @PostMapping
    public String writeMessage(@RequestParam String message, Model model) {
        Message mes = new Message(message);
//        UserDetailsImpl userDetails = getUserDetails();
//        User user = userDetails.getUser();
//        usersRepository.save(user);
//        mes.setAuthor(user);
        messagesRepository.save(mes);
        model.addAttribute("messages", messagesRepository.findAll());
        return "redirect:/main";
    }

    private UserDetailsImpl getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }
}
