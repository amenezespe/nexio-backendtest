package com.test.hydro.demotest.service;

import com.test.hydro.demotest.model.Comment;
import com.test.hydro.demotest.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Iterable<Comment> findByIdComment(String idComment) {

        return commentRepository.findByIdCommentOrderById(idComment);
    }

    @Override
    public void save(Comment comment) {

        commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {

        commentRepository.deleteById(id);
    }
}
