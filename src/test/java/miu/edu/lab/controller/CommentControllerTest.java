package miu.edu.lab.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import miu.edu.lab.dto.InputCommentDto;
import miu.edu.lab.dto.InputPostDto;
import miu.edu.lab.dto.OutPutCommentDto;
import miu.edu.lab.model.Comment;
import miu.edu.lab.model.Post;
import miu.edu.lab.model.User;
import miu.edu.lab.service.CommentService;
import miu.edu.lab.service.PostService;
import miu.edu.lab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CommentControllerTest {
  private ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private PostService postService;
  
  @Autowired
  private CommentService commentService;
  
  @Autowired
  private UserService userService;
  private Post post;
  private User user;
  
  @BeforeEach
  void setUp() {
    user = new User("user1");
    userService.create(user);
    post = postService.create(new InputPostDto("title1", "content1", user.getId()));
  }
  
  @Test
  void testDelete() throws Exception {
    Comment comment = commentService.create(new InputCommentDto("comment2", post.getId()));
    mockMvc.perform(delete("/api/v1/comments/%s".formatted(comment.getId()))).andExpect(status().isNoContent());
  }
  
  @Test
  void testGetAll() throws Exception {
    commentService.create(new InputCommentDto("comment2", post.getId()));
    MvcResult response =
        mockMvc
            .perform(get("/api/v1/comments"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();

    OutPutCommentDto[] comments = objectMapper.readValue(response.getResponse().getContentAsByteArray(), OutPutCommentDto[].class);
    assertEquals(1, comments.length);
  }
    
  @Test
  void testFindById() throws Exception {
    Comment comment = commentService.create(new InputCommentDto("comment2", post.getId()));
    MvcResult response =
        mockMvc
            .perform(get("/api/v1/comments/%s".formatted(comment.getId())))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();

    OutPutCommentDto commentDto = objectMapper.readValue(response.getResponse().getContentAsByteArray(), OutPutCommentDto.class);
    assertEquals(comment.getId(), commentDto.id());
    assertEquals(comment.getName(), commentDto.name());
  }
  
  @Test
  void testAddComment() throws Exception {
    mockMvc
        .perform(
            post("/api/v1/comments")
                .content(
                    """
                {
                  "post": %d,
                  "name": "Sample Comment"
                }
                """.formatted(post.getId()))
                .contentType("application/json"))
        .andExpect(status().isCreated());
    
    MvcResult response =
        mockMvc
            .perform(get("/api/v1/comments"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();

    OutPutCommentDto[] comments = objectMapper.readValue(response.getResponse().getContentAsByteArray(), OutPutCommentDto[].class);
    assertEquals(1, comments.length);
  }
  
  @Test
  void testGetCommentByPostAndUser() throws Exception {
    Comment comment = commentService.create(new InputCommentDto("comment1", post.getId()));
    MvcResult result =
        mockMvc
            .perform(
                get(
                    "/api/v1/users/%d/posts/%d/comments/%d"
                        .formatted(user.getId(), post.getId(), comment.getId())))
            .andExpect(status().isOk())
            .andReturn();
    OutPutCommentDto commentDto = objectMapper.readValue(result.getResponse().getContentAsString(), OutPutCommentDto.class);
    assertEquals(comment.getId(), commentDto.id());
  }
}
