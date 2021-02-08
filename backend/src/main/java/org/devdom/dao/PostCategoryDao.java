package org.devdom.dao;

import org.devdom.model.Post;
import org.devdom.model.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO For PostCategory entity, uses all default methods by JpaRepository
@Repository
public interface PostCategoryDao extends JpaRepository<PostCategory, Integer> {
}
