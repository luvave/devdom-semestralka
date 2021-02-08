package org.devdom.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Entity model for Tool
//Each tool can have many categories, each category can be assigned to many tools
@Entity
@Table(name="nastroj")
public class Tool {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idnastroj",nullable=false)
    private Integer id;
    @Column(name="nazev",nullable=false)
    private String name;
    @Column(name="programovaci_jazyk",nullable=false)
    private String programmingLanguage;
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "kategorie_nastroje_nastroj",
            joinColumns = @JoinColumn(name = "nastroj"),
            inverseJoinColumns = @JoinColumn(name = "kategorie")
    )
    private Set<ToolCategory> toolCategories = new HashSet<>();
    @Transient
    private List<String> errors = new ArrayList<>();

    public Tool() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public void addCategory(ToolCategory category) {
        toolCategories.add(category);
        category.getTools().add(this);
    }

    public void removeCategory(ToolCategory category) {
        toolCategories.remove(category);
        category.getTools().remove(this);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error){
        this.errors.add(error);
    }
}
