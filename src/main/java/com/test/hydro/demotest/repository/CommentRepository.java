package com.test.hydro.demotest.repository;

import com.test.hydro.demotest.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository  extends CrudRepository<Comment, Long> {

    Iterable<Comment> findByIdCommentOrderById(String idComment);


}