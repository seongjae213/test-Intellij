package com.kh.todoManager.post.service;

import com.kh.todoManager.post.model.dao.TodoMapper;
import com.kh.todoManager.post.model.vo.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoMapper tDAO;
    public TodoService(TodoMapper tDAO) {
        this.tDAO = tDAO;
    }

    public List<Todo> selectTodoList(String id){
        return tDAO.selectTodoList(id);
    }

    public Todo addTodo(Todo todo) {
        Todo newTodo = null;
        int result = tDAO.addTodo(todo);
        if(result > 0){
            newTodo = tDAO.selectByMaxNo(todo.getUserId());
        }
        return newTodo;
    }
}
