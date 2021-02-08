package org.devdom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Entity model for ToolCategory, all default
@Entity
@Table(name = "kategorie_nastroje")
public class ToolCategory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idkatnastroje",nullable=false)
    private Integer id;
    @Column(name="kategorie",nullable=false)
    private String categoryName;
    @ManyToMany(mappedBy = "toolCategories")
    @JsonBackReference
    private Set<Tool> tools = new HashSet<>();

    public ToolCategory(String categoryName){
        this.categoryName = categoryName;
    }

    public ToolCategory(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Tool> getTools() {
        return tools;
    }

    public void setTools(Set<Tool> tools) {
        this.tools = tools;
    }
}
