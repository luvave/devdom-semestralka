package org.devdom.service.jwt;

import org.devdom.dao.RegisteredVisitorDao;
import org.devdom.model.RegisteredVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.ArrayList;

//Class that tries find user in database based on nickname.
//The result data is used in comparing.
@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private RegisteredVisitorDao registeredVisitorDao;


    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        System.out.println(nickname);
        RegisteredVisitor user = registeredVisitorDao.findByNickname(nickname);
        //RegisteredVisitor user = new RegisteredVisitor();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + nickname);
        }
        return new org.springframework.security.core.userdetails.User(user.getNickname(), user.getPassword(),
                new ArrayList<>());
    }

}