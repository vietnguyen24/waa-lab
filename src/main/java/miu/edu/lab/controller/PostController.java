package miu.edu.lab.controller;

import java.lang.reflect.Type;
import java.util.List;
import lombok.RequiredArgsConstructor;
import miu.edu.lab.dto.InputPostDto;
import miu.edu.lab.dto.OutPutPostDto;
import miu.edu.lab.model.Post;
import miu.edu.lab.model.User;
import miu.edu.lab.service.PostService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;
  private final ModelMapper modelMapper;
  
  @GetMapping
  public List<OutPutPostDto> getAll() {
    Iterable<Post> posts = postService.getAll();
    Type listType = new TypeToken<List<OutPutPostDto>>() {}.getType();
    return modelMapper.map(posts, listType);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<OutPutPostDto> findById(@PathVariable Long id) {
    Post post = postService.findById(id);
    if (post != null) return ResponseEntity.ok(OutPutPostDto.map(post));
    else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  
  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public OutPutPostDto create(@RequestBody InputPostDto post) {
    return OutPutPostDto.map(postService.create(post));
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    postService.delete(id);
    return ResponseEntity.noContent().build();
  }
  
  @GetMapping("/filter/{title}")
  public ResponseEntity<List<User>> filter(@PathVariable String title) {
    return ResponseEntity.ok(postService.findUserByPostTitle(title));
  }
}
