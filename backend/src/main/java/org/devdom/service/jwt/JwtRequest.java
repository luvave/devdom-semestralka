package org.devdom.service.jwt;

import java.io.Serializable;

//Class that mappes expected login request from client
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String nickname;

    private String password;

    public JwtRequest()

    {

    }

    public JwtRequest(String nickname, String password) {

        this.setNickname(nickname);

        this.setPassword(password);

    }

    public String getNickname() {

        return this.nickname;

    }

    public void setNickname(String nickname) {

        this.nickname = nickname;

    }

    public String getPassword() {

        return this.password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

}