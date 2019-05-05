package com.test.hydro.demotest.controller;

import com.test.hydro.demotest.model.Comment;
import com.test.hydro.demotest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping(value="/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getComments(@PathVariable("id") String idComment) {

        return new ResponseEntity<>(commentService.findByIdComment(idComment), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/comment", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity save(@RequestBody @Valid Comment comment, @PathVariable("id") String idComment) {

        /*
           L’id qui vient sur le lien, je pense que la façon plus correct de faire est de l’ajouter dans du object Comment.
           Cenpendent l’énoncé  demande de faire l’appel rest avec le parementer dans le lien. Donc je l’ai faire mais
           dans une réalité, je demanderais a mon tech leader si on peut utiliser le code plus correct.
         */
        comment.setIdComment(idComment);
        commentService.save(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity update(@RequestBody @Valid Comment comment, @PathVariable("id") String idComment) {

        /*
           À mon avis la meilleure façon de faire serait d'envoyer l'objet complet qui est dans la requette.

         */
        commentService.save(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping (value = "/{id}/comments")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Long id) {

        commentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
