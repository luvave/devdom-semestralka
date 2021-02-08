package org.devdom.rest;

import org.devdom.dao.RegisteredVisitorDao;
import org.devdom.dao.VisitorDao;
import org.devdom.model.RegisteredVisitor;
import org.devdom.model.Visitor;
import org.devdom.service.RegisterAndLoginValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Rest endpoint for working with visitor.. register visitor, and then work with registeredVisitors
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private VisitorDao visitorDao;
    @Autowired
    private RegisteredVisitorDao registeredVisitorDao;

    private RegisterAndLoginValidation registerAndLoginValidation;

    @PostMapping("/visitor")
    public Visitor newVisitor(@Valid @RequestBody Visitor visitor){
        if(visitorDao.existsByIpAddress(visitor.getIpAddress())){
            Visitor existedVisitor = visitorDao.findByIpAddress(visitor.getIpAddress());
            existedVisitor.setLast_visit_date(visitor.getLast_visit_date());
            existedVisitor.setLast_visit_time(visitor.getLast_visit_time());
            return visitorDao.save(existedVisitor);
        }
        else{
            return visitorDao.save(visitor);
        }
    }

    @GetMapping("/allvisitors")
    public List<Visitor> getAllVisitors() {
        return visitorDao.findAll();
    }

    @GetMapping("/allregistered")
    public List<RegisteredVisitor> getAllRegVisitors() {
        return registeredVisitorDao.findAll();
    }

    @PostMapping("/register")
    public RegisteredVisitor registerVisitor(@Valid @RequestBody RegisteredVisitor visitor){
        registerAndLoginValidation = new RegisterAndLoginValidation();
        boolean validated = registerAndLoginValidation.registerValidation(visitor);
        if(validated){
            if(!visitorDao.existsByIpAddress(visitor.getVisitor().getIpAddress())){
                visitorDao.save(visitor.getVisitor());
            }
            Visitor visitorRef = visitorDao.findByIpAddress(visitor.getVisitor().getIpAddress());
            visitor.setVisitor(visitorRef);
            visitor.generateSalt();
            visitor.registerPassword();
            return registeredVisitorDao.save(visitor);
        }
        else{
            return visitor;
        }
    }

}
