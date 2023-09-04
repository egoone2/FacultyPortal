package ru.osokin.portalfbi.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.osokin.portalfbi.models.Role;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.repositories.UsersRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UsersRepository visitorsRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(User visitor) {
        visitor.setPassword(passwordEncoder.encode(visitor.getPassword()));
        visitor.setRole(Role.ROLE_USER);
        visitorsRepository.save(visitor);
    }
}
