package org.devdom;

import org.devdom.model.RegisteredVisitor;
import org.devdom.model.Visitor;
import org.devdom.service.PasswordHashing;
import org.devdom.service.RegisterAndLoginValidation;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RegisteredVisitorTest {

    @Test
    public void registerPassword_validPassword_generatedHashedPasswordEqualsExpected(){
        //ASSERT
        RegisteredVisitor visitor = new RegisteredVisitor();
        String testPassword = "TestovaciHeslo99";
        visitor.setPassword(testPassword);
        visitor.generateSalt();
        String generatedSalt = visitor.getSalt();
        PasswordHashing passwordHashing = new PasswordHashing();
        String expected = passwordHashing.generateHash(generatedSalt+testPassword);
        //ACT
        visitor.registerPassword();
        //ASSERT
        assertEquals(expected, visitor.getPassword());
    }

    @ParameterizedTest(name = "{0}  {1}  {2}")
    @CsvSource({"test, pass, aaa", "none, 0, 5"})
    public void registerValidation_invalidRegistrationData_registeredVisitorsErrorsListNotEmpty(String nickname, String password, String email){
        //ARRANGE
        RegisteredVisitor registeredVisitor = new RegisteredVisitor();
        registeredVisitor.setNickname(nickname);
        registeredVisitor.setEmail(email);
        registeredVisitor.setPassword(password);
        RegisterAndLoginValidation registerAndLoginValidation = new RegisterAndLoginValidation();
        //ACT
        registerAndLoginValidation.registerValidation(registeredVisitor);
        //ASSERT
        assertTrue(registeredVisitor.getErrors().size()>0);

    }

}
