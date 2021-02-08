package org.devdom.dao;

import com.vladmihalcea.hibernate.type.basic.Inet;
import org.devdom.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO For Visitor entity, uses all default methods by JpaRepository
@Repository
public interface VisitorDao extends JpaRepository<Visitor, Integer>{

    //Returns true if Visitor with given ip address exists
    boolean existsByIpAddress(Inet ip_address);
    //Return Visitor by its ip address
    Visitor findByIpAddress(Inet ip_address);
}
