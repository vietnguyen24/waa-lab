package miu.edu.lab.service;

import java.util.List;
import java.util.Optional;
import miu.edu.lab.dto.InputCommentDto;
import miu.edu.lab.dto.OutPutCommentDto;
import miu.edu.lab.model.Comment;

public interface CommentService {
  List<Comment> getAll();
  Optional<Comment> findById(Long id);
  Comment create(InputCommentDto commentDto);
  void delete(Comment comment);
  OutPutCommentDto findCommentByUserIdAndPostId(Long userId, Long postId, Long commentId);
}
