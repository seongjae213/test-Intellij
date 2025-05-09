package com.kh.todoManager.post.controller;

import com.kh.todoManager.post.model.vo.Todo;
import com.kh.todoManager.post.service.TodoService;
import com.kh.todoManager.user.model.vo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TodoController {
    private final TodoService tSer;
    public TodoController(TodoService tSer) {
        this.tSer = tSer;
    }

    /**
     * 할일 목록 조회
     * [GET] /todo
     * @return List<Todo> 할일 목록 (전체)
     */
    @GetMapping("/todo")
    public List<Todo> todoList(HttpSession session) {
        User user = (User)session.getAttribute("loginUser");
        String id = user.getUserId();

        List<Todo> list = tSer.selectTodoList(id);
        return list;
    }

    @PostMapping("/addTodo")
    public ResponseEntity<Object> addTodo(@RequestBody Map<String, Object> requestBody, HttpSession session){
        String content = (String)requestBody.get("content");

        User loginUser = (User)session.getAttribute("loginUser");
        String id = loginUser.getUserId();

        Todo todo = new Todo();
        todo.setContent(content);
        todo.setUserId(id);
        System.out.println(todo);

        Todo result = tSer.addTodo(todo);
        if(result != null) {
            //추가 성공 시 success / body안에 설정한 제네릭 값을 넣으면 됨! Todo 객체같은것도 가능!
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }

    }

}
