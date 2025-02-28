package miu.edu.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.lab.model.Post;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutPutPostDto {
  private Long id;
  private String title;
  private String content;
  private Long userId;
  
  public static OutPutPostDto map(Post post) {
    return new OutPutPostDto(post.getId(), post.getTitle(), post.getContent(), post.getUser().getId());
  }
}
