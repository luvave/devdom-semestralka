package org.devdom.dao;

import com.vladmihalcea.hibernate.type.basic.Inet;
import org.devdom.model.RegisteredVisitor;
import org.devdom.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO For RegisteredVisitor entity, uses all default methods by JpaRepository.
//Extended by two custom methods
@Repository
public interface RegisteredVisitorDao extends JpaRepository<RegisteredVisitor, Integer>{

    //Returns true if RegisteredVisitor with given nickname exists, else false
    boolean existsByNickname(String nickname);
    //Return RegisteredVisitor by nickname
    RegisteredVisitor findByNickname(String nickname);
}
