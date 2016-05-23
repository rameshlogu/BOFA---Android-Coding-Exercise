package com.sample.todoapp.data;

/**
 * This is a Model for TODO activities
 * Created by rameshloganathan on 23/05/16.
 */
public class ToDo {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
