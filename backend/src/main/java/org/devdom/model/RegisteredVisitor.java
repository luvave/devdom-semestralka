package org.devdom.model;

import org.devdom.service.PasswordHashing;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Entity model for RegisteredVisitor
//It extends Visitor table, visitor can have many RegisteredVisitors (accounts)
//Special thing is password with salt hashing. However login function is deprecated. Using JWT system for login
@Entity
@Table(name="registrovany_navstevnik")
public class RegisteredVisitor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idregistrovanynavstevnik",nullable=false)
    private int id;
    @ManyToOne(targetEntity=Visitor.class)
    @JoinColumn(name="navstevnik",nullable=false)
    private Visitor visitor;
    @Column(name="prezdivka",nullable=false)
    private String nickname;
    @Column(name="email",nullable=false)
    private String email;
    @Column(name="heslo",nullable=false)
    private String password;
    @Column(name="salt",nullable=false)
    private String salt;
    @Transient
    private PasswordHashing passwordHashing = new PasswordHashing();
    @Transient
    private List<String> errors = new ArrayList<>();

    public RegisteredVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public RegisteredVisitor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public void generateSalt(){
        this.salt = passwordHashing.generateSalt();
    }

    public void registerPassword(){
        String saltedPassword = this.salt+this.password;
        this.password = passwordHashing.generateHash(saltedPassword);
    }

    public Boolean login(String username, String logPassword) {
        Boolean isAuthenticated = false;
        // remember to use the same SALT value use used while storing password
        // for the first time.
        String saltedPassword = this.salt+logPassword;
        String hashedPassword = passwordHashing.generateHash(saltedPassword);

        if(hashedPassword.equals(this.password)){
            isAuthenticated = true;
        }else{
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error){
        this.errors.add(error);
    }
}
