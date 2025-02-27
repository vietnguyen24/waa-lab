package miu.edu.lab.controller;

import java.lang.reflect.Type;
import java.util.List;
import lombok.RequiredArgsConstructor;
import miu.edu.lab.dto.PostDto;
import miu.edu.lab.dto.UserDto;
import miu.edu.lab.model.Post;
import miu.edu.lab.model.User;
import miu.edu.lab.service.UserService;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final ModelMapper modelMapper;
  
  @GetMapping
  public List<UserDto> getAll() {
    Iterable<User> users = userService.getAll();
    Type listType = new TypeToken<List<UserDto>>() {}.getType();
    return modelMapper.map(users, listType);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findById(@PathVariable Long id) {
    User user = userService.findById(id);
    if (user != null) return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody User user) {
    userService.create(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
