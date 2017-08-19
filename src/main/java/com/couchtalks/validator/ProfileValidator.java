package com.couchtalks.validator;

import com.couchtalks.entity.User;
import com.couchtalks.entity.web.UserForm;
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
public class ProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileValidator.class.isAssignableFrom(clazz);
    }


    @Autowired
    private UserService userService;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void validate(Object target, Errors errors) {

        UserForm userForm = (UserForm) target;

        if (userForm.getProfilePicture() != null) {
            if (!(userForm.getPassword() == null)) {
                if (userForm.getNewPassword() == null) {
                    errors.rejectValue("newPassword", "Required");
                } else if (userForm.getRepeatPassword() == null) {
                    errors.rejectValue("repeatPassword", "Required");
                }
            } else {
                errors.rejectValue("password", "Required");
            }

            if (userForm.getNewPassword() != null) {
                if (userForm.getNewPassword().length() < 6 || userForm.getNewPassword().length() > 32) {
                    errors.rejectValue("newPassword", "Size.userForm.password");
                }
            }

            if (null != userForm.getRepeatPassword() && !userForm.getRepeatPassword().equals(userForm.getNewPassword())) {
                errors.rejectValue("repeatPassword", "Different.userForm.repeatPassword");
            }


            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(userForm.getEmail());
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
            if (!matcher.matches()) {
                errors.rejectValue("email", "Email.incorrect");
            }
        }

    }
}
