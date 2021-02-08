package org.devdom;


import org.devdom.dao.VisitorDao;
import org.devdom.model.RegisteredVisitor;
import org.devdom.model.Visitor;
import org.devdom.rest.UserController;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private VisitorDao visitorDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllVisitors_validListOfVisitors_ipAddressOfFirstVisitorEqualsArranged(){
        //ARRANGE
        Visitor visitor1 = new Visitor();
        visitor1.setIpAddress("192.88.88.88");
        Visitor visitor2 = new Visitor();
        visitor2.setIpAddress("7.7.7.7");
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(visitor1);
        visitorList.add(visitor2);
        when(visitorDao.findAll()).thenReturn(visitorList);
        //ACT
        List<Visitor> result = userController.getAllVisitors();
        //ASSERT
        assertEquals(visitor1.getIpAddress().getAddress(),result.get(0).getIpAddress().getAddress());
    }

    @Test
    public void registerVisitor_passwordWith2Characters_visitorsFirstErrorEqualsShortPasswordString(){
        //ARRANGE
        RegisteredVisitor registeredVisitor = new RegisteredVisitor();
        registeredVisitor.setNickname("PepaNovak");
        registeredVisitor.setPassword("A2");
        registeredVisitor.setEmail("pepa.novak@best.mail");
        //ACT
        RegisteredVisitor result = userController.registerVisitor(registeredVisitor);
        //ASSERT
        assertEquals("Password is wrong length, please set password between 6-20 characters.",result.getErrors().get(0));
    }
}
