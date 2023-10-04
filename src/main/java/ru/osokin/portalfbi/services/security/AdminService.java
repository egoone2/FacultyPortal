package ru.osokin.portalfbi.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.osokin.portalfbi.models.Role;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.repositories.UsersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UsersRepository usersRepository;

    @Transactional
    public void giveAdminRole(Long id) {
        giveRoleToUser(id, Role.ROLE_ADMIN);
    }


    @Transactional
    public void giveTeacherRole(Long id) {
        giveRoleToUser(id, Role.ROLE_TEACHER);
    }

    @Transactional
    public void giveStudentRole(Long id) {
        giveRoleToUser(id, Role.ROLE_STUDENT);
    }


    private void giveRoleToUser(Long id, Role role) {
        User user = usersRepository.findById(id).get();
        user.setRole(role);
        usersRepository.save(user);
    }


    public List<User> findByKeyword(String keyword) {
        if (keyword != null) return usersRepository.findByKeyword(keyword);

        return usersRepository.findAll();
    }
}
