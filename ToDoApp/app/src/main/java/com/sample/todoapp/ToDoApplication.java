package com.sample.todoapp;

import android.app.Application;

import com.sample.todoapp.data.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rameshloganathan on 23/05/16.
 */
public class ToDoApplication extends Application{
    private List<ToDo> toDoList;

    @Override
    public void onCreate() {
        super.onCreate();
        //Init data
        toDoList = new ArrayList<ToDo>();
    }

    public List<ToDo> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<ToDo> toDoList) {
        this.toDoList = toDoList;
    }

    public void addTodo(ToDo toDo){
        toDoList.add(toDo);
    }
}
