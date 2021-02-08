package org.devdom.dao;

import org.devdom.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO For Tool entity, uses all default methods by JpaRepository
@Repository
public interface ToolDao extends JpaRepository<Tool, Integer> {
}
