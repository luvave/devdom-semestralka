package org.devdom.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

//Entity model for comments, all defaults
@Entity
@Table(name="komentar")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idkomentar",nullable=false)
    private int id;

    @Column(name="text_komentare",nullable=false)
    private String questionText;

    @Column(name="datum",nullable=false)
    private Date date;

    @Column(name="cas",nullable=false)
    private Time time;

    public Comment(){
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
