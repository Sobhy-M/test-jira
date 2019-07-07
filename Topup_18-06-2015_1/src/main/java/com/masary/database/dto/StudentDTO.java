/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Melad
 */
public class StudentDTO {

    private String name, faculty, stage, exp, id;

    public StudentDTO(String name, String faculty, String stage, String exp, String id) {
        this.name = name;
        this.faculty = faculty;
        this.stage = stage;
        this.exp = exp;
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public void setOrder(String exp) {
        this.exp = exp;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
