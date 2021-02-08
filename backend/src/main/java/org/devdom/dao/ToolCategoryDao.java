package org.devdom.dao;

import org.devdom.model.ToolCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO For ToolCategory entity, uses all default methods by JpaRepository
@Repository
public interface ToolCategoryDao extends JpaRepository<ToolCategory, Integer> {
}
