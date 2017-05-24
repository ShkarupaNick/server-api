package com.couchtalks.validator;

import com.couchtalks.entity.User;
import com.couchtalks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ShkarupaN on 13.04.2017.
 */

@Component
public class RegistrationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationValidator.class.isAssignableFrom(clazz);
    }


    @Autowired
    private UserService userService;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getRepeatPassword().equals(user.getPassword())) {
            errors.rejectValue("repeatPassword", "Different.userForm.repeatPassword");
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(user.getEmail());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (!matcher.matches()) {
            errors.rejectValue("email", "Email.incorrect");
        }
    }
}
