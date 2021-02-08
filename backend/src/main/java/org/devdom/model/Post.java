package org.devdom.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Entity model for Post
//Each post can have many categories, each category can be assigned to many posts
@Entity
@Table(name="prispevek")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idprispevek",nullable=false)
    private int id;
    @Column(name="obsah_prispevku",nullable=false)
    private String content;
    @Column(name="nadpis",nullable=false)
    private String title;
    @ManyToOne(targetEntity=RegisteredVisitor.class)
    @JoinColumn(name="registrovany_navstevnik",nullable=false)
    private RegisteredVisitor author;
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "kategorie_prispevku_prispevek",
            joinColumns = @JoinColumn(name = "prispevek"),
            inverseJoinColumns = @JoinColumn(name = "kategorie")
    )
    private Set<PostCategory> postCategories = new HashSet<>();
    @Transient
    private List<String> errors = new ArrayList<>();

    public Post(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RegisteredVisitor getAuthor() {
        return author;
    }

    public void setAuthor(RegisteredVisitor author) {
        this.author = author;
    }

    public void addCategory(PostCategory category) {
        postCategories.add(category);
        category.getPosts().add(this);
    }

    public void removeCategory(PostCategory category) {
        postCategories.remove(category);
        category.getPosts().remove(this);
    }

    public Set<PostCategory> getPostCategories() {
        return postCategories;
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
