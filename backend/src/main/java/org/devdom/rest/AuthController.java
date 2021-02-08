package org.devdom.rest;

import org.devdom.dao.RegisteredVisitorDao;
import org.devdom.model.RegisteredVisitor;
import org.devdom.service.jwt.JwtRequest;
import org.devdom.service.jwt.JwtResponse;
import org.devdom.service.jwt.JwtToken;
import org.devdom.service.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

//Rest endpoint used only for login
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RegisteredVisitorDao registeredVisitorDao;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        String putNickname = authenticationRequest.getNickname();
        String saltedPassword = authenticationRequest.getPassword();
        RegisteredVisitor registeredVisitor = null;
        if(registeredVisitorDao.existsByNickname(putNickname)){
            registeredVisitor = registeredVisitorDao.findByNickname(putNickname);
            saltedPassword = registeredVisitor.getSalt()+saltedPassword;
        }

        authenticate(putNickname, saltedPassword);

        final UserDetails userDetails = jwtUserDetailsService

                .loadUserByUsername(putNickname);
        final String token = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String nickame, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(nickame, password));

        } catch (DisabledException e) {

            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {

            throw new Exception("INVALID_CREDENTIALS", e);

        }

    }

}
