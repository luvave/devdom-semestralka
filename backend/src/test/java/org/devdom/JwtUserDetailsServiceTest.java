package org.devdom;

import org.devdom.service.jwt.JwtToken;
import org.devdom.service.jwt.JwtUserDetailsService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUserDetailsServiceTest {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Test
    public void loadUserByUsername_invalidUsername_throwsUsernameNotFoundException(){
        //ARRANGE
        String nickname = "NeexistujiciPrezdivka";
        //ACT and ASSERT
        assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername(nickname));
    }
}
