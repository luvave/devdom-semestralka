package org.devdom;

import org.devdom.service.jwt.JwtToken;
import org.devdom.service.jwt.JwtUserDetailsService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTokenTest {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtToken jwtToken;

    @Test
    public void generateToken_validUsername_startOfGeneratedStringEqualsBearer(){
        //ARRANGE
        String nickname = "novakjan2";
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(nickname);
        String expectedWord = "Bearer";
        //ACT
        String token = jwtToken.generateToken(userDetails);
        int i = token.indexOf(' ');
        String resultWord = token.substring(0, i);
        //ASSERT
        assertEquals(expectedWord, resultWord);
    }
}
