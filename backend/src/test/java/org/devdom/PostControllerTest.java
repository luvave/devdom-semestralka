package org.devdom;

import org.devdom.dao.PostCategoryDao;
import org.devdom.dao.PostDao;
import org.devdom.model.Post;
import org.devdom.model.PostCategory;
import org.devdom.rest.PostController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostDao postDao;

    @Mock
    private PostCategoryDao postCategoryDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllPostCategories_validListOfPostCategories_nameOfFirstCategoryEqualsArranged(){
        //ARRANGE
        PostCategory postCategory1 = new PostCategory();
        postCategory1.setCategoryName("Cat1");
        PostCategory postCategory2 = new PostCategory();
        postCategory2.setCategoryName("Cate2");
        List<PostCategory> postCategoryList = new ArrayList<>();
        postCategoryList.add(postCategory1);
        postCategoryList.add(postCategory2);
        when(postCategoryDao.findAll()).thenReturn(postCategoryList);
        //ACT
        List<PostCategory> result = postController.getAllPostCategories();
        //ASSERT
        assertEquals(postCategory1.getCategoryName(),result.get(0).getCategoryName());
    }

    @Test
    public void getAllPostsFromCategory_validPostsFromGivenCategory_resultPostListSizeEquals2(){
        //ARRANGE
        PostCategory postCategory1 = new PostCategory();
        postCategory1.setCategoryName("Cat1");
        PostCategory postCategory2 = new PostCategory();
        postCategory2.setCategoryName("Cate2");
        List<PostCategory> postCategoryList = new ArrayList<>();
        Post post = new Post();
        post.setTitle("Post1");
        Post post2 = new Post();
        post2.setTitle("Post2");
        Post post3 = new Post();
        post3.setTitle("Post3");
        post.addCategory(postCategory1);
        post2.addCategory(postCategory1);
        post3.addCategory(postCategory2);
        when(postCategoryDao.findAll()).thenReturn(postCategoryList);
        //ACT
        Set<Post> result = postController.getAllPostsFromCategory(postCategory1);
        //ASSERT
        assertEquals(2,result.size());

    }

}
