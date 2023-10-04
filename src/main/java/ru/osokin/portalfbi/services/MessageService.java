package ru.osokin.portalfbi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.osokin.portalfbi.models.Message;
import ru.osokin.portalfbi.models.ServerFile;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.repositories.MessagesRepository;
import ru.osokin.portalfbi.security.UserDetailsImpl;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessagesRepository messagesRepository;

    public List<Message> getAll() {
        return messagesRepository.findAll();
    }

    @Transactional
    public void newMessage(String message, ServerFile file) {
        Message messageToWrite = new Message(message);
        UserDetailsImpl userDetails = getUserDetails();
        User user = userDetails.getUser();
        messageToWrite.setAuthor(user);
        String uuid = UUID.randomUUID().toString();
        messageToWrite.setAuthorName(user.getName() + " " + user.getSurname());
        messageToWrite.getFiles().add(file);
        file.setMessage(messageToWrite);
        messagesRepository.save(messageToWrite);
    }

    private UserDetailsImpl getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }

}
