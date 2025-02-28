package miu.edu.lab.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import miu.edu.lab.dto.InputCommentDto;
import miu.edu.lab.dto.OutPutCommentDto;
import miu.edu.lab.model.Comment;
import miu.edu.lab.service.CommentService;
import org.springframework.data.repository.query.Param;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
  private final CommentService commentService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<OutPutCommentDto> getAll() {
    List<Comment> list = commentService.getAll();
    return list.stream().map(c -> OutPutCommentDto.map(c)).collect(Collectors.toList());
  }
  
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<OutPutCommentDto> findById(@PathVariable Long id) {
    Optional<Comment> c = commentService.findById(id);
    if (c.isPresent()) return ResponseEntity.ok(OutPutCommentDto.map(c.get()));
    else return ResponseEntity.notFound().build();
  }
  
  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  void create(@RequestBody InputCommentDto comment) {
    commentService.create(comment);
  }
  
  @DeleteMapping("/{id}")
  ResponseEntity delete(@PathVariable Long id) {
    Optional<Comment> c = commentService.findById(id);
    if (c.isEmpty()) return ResponseEntity.notFound().build();
    else {
      commentService.delete(c.get());
      return ResponseEntity.noContent().build();
    }
  }
}
