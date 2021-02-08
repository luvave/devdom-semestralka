package org.devdom.dao;

import org.devdom.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO For Question entity, uses all default methods by JpaRepository
@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
}