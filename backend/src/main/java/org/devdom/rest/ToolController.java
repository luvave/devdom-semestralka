package org.devdom.rest;

import org.devdom.dao.ToolCategoryDao;
import org.devdom.dao.ToolDao;
import org.devdom.model.Post;
import org.devdom.model.PostCategory;
import org.devdom.model.Tool;
import org.devdom.model.ToolCategory;
import org.devdom.service.ToolPostQuestionCreationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

//Rest endpoint for working with tools
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping(value = "/tool")
public class ToolController {

    @Autowired
    private ToolDao toolDao;
    @Autowired
    private ToolCategoryDao toolCategoryDao;

    private ToolPostQuestionCreationValidation toolPostQuestionCreationValidation;

    @GetMapping("/alltools")
    public List<Tool> getAllTools(){
        return toolDao.findAll();
    }

    @GetMapping("/alltoolcategories")
    public List<ToolCategory> getAllToolCategories() {
        return toolCategoryDao.findAll();
    }

    @PostMapping("/newtoolcategory")
    public ToolCategory newToolCategory(@Valid @RequestBody ToolCategory toolCategory){
        return toolCategoryDao.save(toolCategory);
    }

    @PostMapping("/gettoolsfromcat")
    public Set<Tool> getAllToolsFromCategory(@Valid @RequestBody ToolCategory toolCategory) {
        return toolCategoryDao.findAll().get(toolCategory.getId()).getTools();
    }

    @PostMapping("/newtool")
    public Tool newTool(@Valid @RequestBody Tool tool) {
        toolPostQuestionCreationValidation = new ToolPostQuestionCreationValidation();
        boolean validated = toolPostQuestionCreationValidation.newToolValidation(tool);
        if(validated){
            return toolDao.save(tool);
        }
        else{
            return tool;
        }
    }

}
