package io.agileinteligence.ppmtool.validation;

import io.agileinteligence.ppmtool.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals((aClass)); // we use User class to validate
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User)o;

        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "Length", "Password must be at least 6 charactesrs");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Passwords must match");
        }

        // confirmPassword

    }
}
