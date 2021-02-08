package org.devdom.service;

import org.devdom.model.Post;
import org.devdom.model.Question;
import org.devdom.model.RegisteredVisitor;
import org.devdom.model.Tool;

import java.util.ArrayList;
public class ToolPostQuestionCreationValidation {

    public ToolPostQuestionCreationValidation() {
    }

    public boolean newPostValidation(Post post){
        post.setErrors(new ArrayList<>());
        boolean validated = true;
        String title = post.getTitle();
        if(title.length()<5){
            validated = false;
            post.addError("Title should have more than 5 characters");
        }
        return validated;
    }

    public boolean newToolValidation(Tool tool){
        tool.setErrors(new ArrayList<>());
        boolean validated = true;
        String name = tool.getName();
        if(name.length()>30){
            validated = false;
            tool.addError("Name should not have more than 30 characters");
        }
        return validated;
    }

    public boolean newQuestionValidation(Question question){
        question.setErrors(new ArrayList<>());
        boolean validated = true;
        String text = question.getQuestionText();
        if(text.length()<6 || text.length()>70){
            validated = false;
            question.addError("Question should be between 6 and 70 characters.");
        }
        return validated;
    }
}
