/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Melad
 */
public class CategoryDTO {

    private String name;
    private int Id;
    private Object avilableValues[];

    public CategoryDTO(String name, int Id) {
        this.name = name;
        this.Id = Id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object[] getAvilableValues() {
        return avilableValues;
    }

    public void setAvilableValues(Object[] avilableValues) {
        this.avilableValues = avilableValues;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" + "name=" + name + ", Id=" + Id + ", avilableValues=" + avilableValues + '}';
    }

}
