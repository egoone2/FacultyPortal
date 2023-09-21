package ru.osokin.portalfbi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.repositories.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public Optional<User> getById(Long id) {
        return usersRepository.findById(id);
    }

    public Optional<User> getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
