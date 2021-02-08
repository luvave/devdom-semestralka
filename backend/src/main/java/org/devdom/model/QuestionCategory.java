package org.devdom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Entity model for QuestionCategory, all default
@Entity
@Table(name = "kategorie_otazky")
public class QuestionCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idkatotazky",nullable=false)
    private Integer id;
    @Column(name="kategorie",nullable=false)
    private String categoryName;
    @ManyToMany(mappedBy = "questionCategories")
    @JsonBackReference
    private Set<Question> questions = new HashSet<>();

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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
