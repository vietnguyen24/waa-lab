package miu.edu.lab.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import miu.edu.lab.dto.PostDto;
import miu.edu.lab.model.Post;
import miu.edu.lab.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostController.class)
public class PostControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private PostService postService;

  @MockBean private ModelMapper modelMapper;

  @Autowired private ObjectMapper objectMapper;

  private Post post;
  private PostDto postDto;

  @BeforeEach
  void setUp() {
    post = new Post(1L, "Sample Title", "Sample Content", "Author Name");
    postDto = new PostDto(1L, "Sample Title", "Sample Content", "Author Name");
  }

  @Test
  void testGetAll() throws Exception {
    when(postService.getAll()).thenReturn(List.of(post));
    when(modelMapper.map(post, PostDto.class)).thenReturn(postDto);

    mockMvc.perform(get("/api/v1/posts")).andExpect(status().isOk());
  }

  @Test
  void testFindById() throws Exception {
    when(postService.findById(1L)).thenReturn(post);
    when(modelMapper.map(post, PostDto.class)).thenReturn(postDto);

    mockMvc.perform(get("/api/v1/posts/1")).andExpect(status().isOk());
  }

  @Test
  void testCreate() throws Exception {
    mockMvc
        .perform(
            post("/api/v1/posts")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(post)))
        .andExpect(status().isCreated());
  }
}
