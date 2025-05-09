package com.kh.todoManager.post.model.dao;

import com.kh.todoManager.post.model.vo.Todo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoMapper {
    private final SqlSession sqlSession;
    public TodoMapper(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Todo> selectTodoList(String id) {
        return sqlSession.selectList("todoMapper.selectTodoList", id);
    }

    public int addTodo(Todo todo) {
        return sqlSession.insert("todoMapper.addTodo", todo);
    }

    public Todo selectByMaxNo(String userId) {
        return sqlSession.selectOne("todoMapper.selectByMaxNo", userId);
    }
}
