package org.devdom;

import org.devdom.dao.QuestionDao;
import org.devdom.model.Question;
import org.devdom.rest.QuestionController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionControllerTest {

    @InjectMocks
    @Spy
    private QuestionController questionController;

    @Mock
    private QuestionDao questionDao;

    @Mock
    private Random random;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRandomQuestions_validListOfQuestions_textOfRandomQuestionEqualsArranged(){
        //ARRANGE
        Question question = new Question();
        question.setQuestionText("How are you?");
        Question question2 = new Question();
        question2.setQuestionText("Favorite color?");
        Question question3 = new Question();
        question3.setQuestionText("Whats the time?");
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        questionList.add(question2);
        questionList.add(question3);
        when(questionDao.findAll()).thenReturn(questionList);
        Random randomMock = Mockito.mock(Random.class);
        when(randomMock.nextInt(3)).thenReturn(1);
        when(questionController.getRandom()).thenReturn(randomMock);
        //ACT
        Question result = questionController.getRandomQuestions();
        //ASSERT
        assertEquals(question2.getQuestionText(),result.getQuestionText());
    }
}
