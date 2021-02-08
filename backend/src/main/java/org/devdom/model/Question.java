package org.devdom.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Entity model for Question
//Each question can have many categories, each category can be assigned to many questions
@Entity
@Table(name="otazka")
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idotazka",nullable=false)
    private int id;

    @Column(name="text_otazky",nullable=false)
    private String questionText;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "kategorie_otazky_otazka",
            joinColumns = @JoinColumn(name = "otazka"),
            inverseJoinColumns = @JoinColumn(name = "kategorie")
    )
    private Set<QuestionCategory> questionCategories = new HashSet<>();

    //Many questions can have the same next question
    @ManyToOne
    @JoinTable(name = "dalsi_otazky",
            joinColumns = {@JoinColumn(name = "otazka")},
            inverseJoinColumns = {@JoinColumn(name = "navazujici_otazka")}
    )
    private Question nextQuestion;

    @OneToMany(mappedBy = "nextQuestion")
    private Set<Question> prevQuestions = new HashSet<>();
    @Transient
    private List<String> errors = new ArrayList<>();

    public Question(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void addCategory(QuestionCategory category) {
        questionCategories.add(category);
        category.getQuestions().add(this);
    }

    public void removeCategory(QuestionCategory category) {
        questionCategories.remove(category);
        category.getQuestions().remove(this);
    }

    public Question getNextQuestion() {
        return nextQuestion;
    }

    public void setNextQuestion(Question nextQuestion) {
        this.nextQuestion = nextQuestion;
        nextQuestion.getPrevQuestions().add(this);
    }

    public Set<Question> getPrevQuestions() {
        return prevQuestions;
    }

    public void setPrevQuestions(Set<Question> prevQuestions) {
        this.prevQuestions = prevQuestions;
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
