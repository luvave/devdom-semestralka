package org.devdom.rest;

import org.devdom.dao.*;
import org.devdom.model.*;
import org.devdom.service.ToolPostQuestionCreationValidation;
import org.devdom.service.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;

//Rest endpoint for working with questions
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionCategoryDao questionCategoryDao;
    @Autowired
    private RegisteredVisitorDao registeredVisitorDao;
    @Autowired
    private JwtToken jwtToken;

    private ToolPostQuestionCreationValidation toolPostQuestionCreationValidation;

    @GetMapping("/allquestion")
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    @GetMapping("/allquestioncategories")
    public List<QuestionCategory> getAllQuestionCategories() {
        return questionCategoryDao.findAll();
    }

    @PostMapping("/newquestioncategory")
    public QuestionCategory newQuestionCategory(@Valid @RequestBody QuestionCategory questionCategory){
        return questionCategoryDao.save(questionCategory);
    }

    @GetMapping("/randomquestion")
    public Question getRandomQuestions() {
        Random rand = getRandom();
        List<Question> allQuestions = questionDao.findAll();
        return allQuestions.get(rand.nextInt(allQuestions.size()));
    }

    @PostMapping("/newquestion")
    public Question newQuestion(@Valid @RequestBody Question question){
        toolPostQuestionCreationValidation = new ToolPostQuestionCreationValidation();
        boolean validated = toolPostQuestionCreationValidation.newQuestionValidation(question);
        if (validated) {
            return questionDao.save(question);
        }
        else{
            return question;
        }

    }

    @PostMapping("/getnextquestion")
    public Question nextQuestion(@Valid @RequestBody Question question){
        Question nextQuestion = question.getNextQuestion();
        return nextQuestion;
    }

    public Random getRandom(){
        return new Random();
    }
}
