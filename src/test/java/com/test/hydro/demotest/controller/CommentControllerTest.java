package com.test.hydro.demotest.controller;

import com.google.gson.Gson;
import com.test.hydro.demotest.Util.HelperFile;
import com.test.hydro.demotest.model.Comment;
import com.test.hydro.demotest.model.CommentsList;
import com.test.hydro.demotest.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class CommentControllerTest {

    private String PATH_APP = "/post";

    private MockMvc mvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(commentController)
                .setControllerAdvice(new ExceptionHandlerControllerTest()).build();


    }

    @Test
    public void getCommentsTest_Return200() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get(PATH_APP+"/999/comments");

        this.mvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }

    @Test
    public void getCommentsTest_ValidList() throws Exception {
        var comments = new Gson().fromJson(HelperFile.loadFileJson("commentsList_999.json"), CommentsList.class);
        Mockito.when(commentService.findByIdComment(anyString())).thenReturn(comments.getComments());

        var requestBuilder = get(PATH_APP+"/999/comments");


        this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(HelperFile.loadFileJson("responseGet999.json")));
    }

    @Test
    public void saveTest_Return200 () throws Exception {
        var comment = new Gson().fromJson(HelperFile.loadFileJson("comment.json"), Comment.class);


        mvc.perform(MockMvcRequestBuilders.post(PATH_APP + "/999/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(comment)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateTest_Return200 () throws Exception {
        var comment = new Gson().fromJson(HelperFile.loadFileJson("comment.json"), Comment.class);


        mvc.perform(MockMvcRequestBuilders.put(PATH_APP + "/999/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(comment)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest_Return200 () throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete(PATH_APP + "/999/comments"))
                .andExpect(status().isOk());
    }


    private String toJson (Object o) {
        return new Gson().toJson(o);
    }


}
