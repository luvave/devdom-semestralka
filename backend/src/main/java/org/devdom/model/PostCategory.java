package org.devdom.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Entity model for PostCategory, all default
@Entity
@Table(name = "kategorie_prispevku")
public class PostCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idkatprispevku",nullable=false)
    private Integer id;
    @Column(name="kategorie",nullable=false)
    private String categoryName;
    @ManyToMany(mappedBy = "postCategories")
    @JsonBackReference
    private Set<Post> posts = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
