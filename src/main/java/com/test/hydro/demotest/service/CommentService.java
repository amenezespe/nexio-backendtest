package com.test.hydro.demotest.service;

import com.test.hydro.demotest.model.Comment;

public interface CommentService {

    Iterable<Comment> findByIdComment(String idComment);

    void save(Comment comment);

    void delete (Long id);

}
