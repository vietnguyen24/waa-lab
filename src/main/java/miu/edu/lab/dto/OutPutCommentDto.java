package miu.edu.lab.dto;

import miu.edu.lab.model.Comment;

public record OutPutCommentDto (Long id, String name, Long postId) {
  
  public static OutPutCommentDto map(Comment comment) {
    return new OutPutCommentDto(comment.getId(), comment.getName(), comment.getPost().getId());
  }
}
