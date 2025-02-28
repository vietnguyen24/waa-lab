package miu.edu.lab.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import miu.edu.lab.dto.InputPostDto;
import miu.edu.lab.dto.OutPutPostDto;
import miu.edu.lab.model.User;
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
public class PostControllerTest {
  @Autowired private MockMvc mockMvc;
  @Autowired private UserService userService;
  @Autowired private PostService postService;
  private ObjectMapper objectMapper = new ObjectMapper();
  private User user;

  @BeforeEach
  void setUp() {
    user = new User("user1");
    userService.create(user);
    postService.create(new InputPostDto("title1", "content1", user.getId()));
  }

  @Test
  void testGetAll() throws Exception {

    MvcResult result = mockMvc.perform(get("/api/v1/posts")).andExpect(status().isOk()).andReturn();
    OutPutPostDto[] posts =
        objectMapper.readValue(result.getResponse().getContentAsString(), OutPutPostDto[].class);
    assertEquals(1, posts.length);
  }

  @Test
  void testFindById() throws Exception {

    MvcResult result =
        mockMvc.perform(get("/api/v1/posts/1")).andExpect(status().isOk()).andReturn();
    OutPutPostDto post =
        objectMapper.readValue(result.getResponse().getContentAsString(), OutPutPostDto.class);
    assertEquals(1, post.getId());
    assertEquals("title1", post.getTitle());
  }

  @Test
  void testCreate() throws Exception {
    MvcResult result =
        mockMvc
            .perform(
                post("/api/v1/posts")
                    .content(
                        """
                      {
                        "title": "Sample Title",
                        "content": "Sample Content",
                        "userId":  %d
                      }
                      """.formatted(user.getId()))
                    .contentType("application/json"))
            .andReturn();
    assertEquals(201, result.getResponse().getStatus(), result.getResponse().getContentAsString());

    OutPutPostDto post =
        objectMapper.readValue(result.getResponse().getContentAsString(), OutPutPostDto.class);
    assertTrue(post.getId() > 0);
    assertEquals("Sample Title", post.getTitle());
    assertEquals("Sample Content", post.getContent());
    assertEquals(user.getId(), post.getUserId());
  }
  
  @Test
  void testFindUserByPostTitle() throws Exception {
    postService.create(new InputPostDto("aa", "content1", user.getId()));
    postService.create(new InputPostDto("bb", "content1", user.getId()));
    MvcResult result = mockMvc.perform(get("/api/v1/posts/filter/aa")).andExpect(status().isOk())
        .andReturn();
    User[] users = objectMapper.readValue(result.getResponse().getContentAsString(), User[].class);
    assertEquals(1, users.length);
    assertEquals(user.getId(), users[0].getId());
  }
}
