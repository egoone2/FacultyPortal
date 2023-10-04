package ru.osokin.portalfbi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.osokin.portalfbi.models.ServerFile;
import ru.osokin.portalfbi.services.files.FileStorageService;
import ru.osokin.portalfbi.services.MessageService;
import ru.osokin.portalfbi.services.files.PreviewPngService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mes")
public class MessagesController {
    private final MessageService messageService;
    private final FileStorageService fileStorageService;

    @GetMapping("/new")
    public String newMessage() {
        return "newMessage";
    }

    @PostMapping
    public String writeMessage(@RequestParam String message, @RequestParam MultipartFile file, Model model)  {
        ServerFile storeFile = fileStorageService.storeFile(file);
        messageService.newMessage(message, storeFile);
        model.addAttribute("messages", messageService.getAll());
        return "redirect:/main";
    }

}
