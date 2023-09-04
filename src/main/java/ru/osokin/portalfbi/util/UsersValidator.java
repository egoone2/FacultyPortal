package ru.osokin.portalfbi.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.services.security.UsersService;

@Component
@RequiredArgsConstructor
public class UsersValidator implements Validator {

    private final UsersService visitorsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (visitorsService.getByUsername(user.getUsername()).isPresent())
            errors.rejectValue("username", "", "Человек с таким именем пользователя уже есть в системе.");
    }
}
