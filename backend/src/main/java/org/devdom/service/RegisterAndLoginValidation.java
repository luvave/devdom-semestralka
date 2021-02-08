package org.devdom.service;

import org.devdom.model.RegisteredVisitor;

import java.util.ArrayList;

public class RegisterAndLoginValidation {
    public RegisterAndLoginValidation() {
    }

    public boolean registerValidation(RegisteredVisitor registeredVisitor){
        registeredVisitor.setErrors(new ArrayList<>());
        boolean validated = true;
        String nickname = registeredVisitor.getNickname();
        if(nickname.length()<3 || nickname.length()>17){
            registeredVisitor.addError("Nickname is wrong length, please set nickname between 3-16 characters.");
            validated = false;
        }
        String password = registeredVisitor.getPassword();
        if(password.length()<6 || password.length()>21){
            registeredVisitor.addError("Password is wrong length, please set password between 6-20 characters.");
            validated = false;
        }
        if(!password.matches(".*\\d.*")){
            registeredVisitor.addError("Password does not contain numbers, please set password with a number.");
            validated = false;
        }
        if(!password.matches(".*[a-zA-Z]+.*")){
            registeredVisitor.addError("Password does not contain letters, please set password with some letters.");
            validated = false;
        }
        String email = registeredVisitor.getEmail();
        if(!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
            registeredVisitor.addError("Email is not a valid email address.");
            validated = false;
        }
        return validated;
    }
}
