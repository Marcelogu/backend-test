package com.nisum.backendtest.util;

import com.nisum.backendtest.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserValidations {

    @Value("${regex.email}")
    private String emailRegex;
    @Value("${regex.password}")
    private String passwordRegex;

    private boolean isEmailValid(String email) {
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        return Pattern.compile(passwordRegex).matcher(password).matches();
    }

    public final Predicate<UserRequestDto> validateMailAndPassword = user -> isEmailValid(user.getEmail()) && isPasswordValid(user.getPassword());


}
