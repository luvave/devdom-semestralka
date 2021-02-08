package org.devdom.dao;

import org.devdom.model.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO For QuestionCategory entity, uses all default methods by JpaRepository
@Repository
public interface QuestionCategoryDao extends JpaRepository<QuestionCategory, Integer> {
}