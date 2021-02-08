package org.devdom.dao;

import org.devdom.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO For Post entity, uses all default methods by JpaRepository
@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
}
