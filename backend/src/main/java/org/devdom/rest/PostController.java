package org.devdom.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.devdom.dao.PostCategoryDao;
import org.devdom.dao.PostDao;
import org.devdom.dao.RegisteredVisitorDao;
import org.devdom.dao.VisitorDao;
import org.devdom.model.Post;
import org.devdom.model.PostCategory;
import org.devdom.model.RegisteredVisitor;
import org.devdom.model.Visitor;
import org.devdom.service.ToolPostQuestionCreationValidation;
import org.devdom.service.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

//Rest endpoint, for working with posts
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostDao postDao;
    @Autowired
    private PostCategoryDao postCategoryDao;
    @Autowired
    private RegisteredVisitorDao registeredVisitorDao;
    @Autowired
    private JwtToken jwtToken;

    private ToolPostQuestionCreationValidation toolPostQuestionCreationValidation;

    @GetMapping("/allposts")
    public List<Post> getAllPosts() {
        return postDao.findAll();
    }

    @GetMapping("/allpostcategories")
    public List<PostCategory> getAllPostCategories() {
        return postCategoryDao.findAll();
    }

    @PostMapping("/newpostcategory")
    public PostCategory newPostCategory(@Valid @RequestBody PostCategory postCategory){
        return postCategoryDao.save(postCategory);
    }

    @PostMapping("/getpostsfromcat")
    public Set<Post> getAllPostsFromCategory(@Valid @RequestBody PostCategory postCategory) {
        List<PostCategory> postCategoryList = postCategoryDao.findAll();
        for(PostCategory cat : postCategoryList){
            if(cat.getCategoryName()==postCategory.getCategoryName()){
                return cat.getPosts();
            }
        }
        return postCategory.getPosts();
    }

    @PostMapping("/newpost")
    public Post newPost(@Valid @RequestBody Post post){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        String nickname = jwtToken.getUsernameFromToken(token);
        RegisteredVisitor author = registeredVisitorDao.findByNickname(nickname);
        post.setAuthor(author);
        toolPostQuestionCreationValidation = new ToolPostQuestionCreationValidation();
        boolean validated = toolPostQuestionCreationValidation.newPostValidation(post);
        if(validated){
            return postDao.save(post);
        }
        else{
            return post;
        }
    }

}
