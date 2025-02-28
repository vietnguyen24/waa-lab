package miu.edu.lab.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import miu.edu.lab.dto.InputCommentDto;
import miu.edu.lab.dto.OutPutCommentDto;
import miu.edu.lab.model.Comment;
import miu.edu.lab.model.Post;
import miu.edu.lab.repo.CommentRepo;
import miu.edu.lab.repo.PostRepo;
import miu.edu.lab.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private final CommentRepo commentRepo;
  private final PostRepo postRepo;
  private final ModelMapper modelMapper;

  @Override
  @Transactional(readOnly = true)
  public List<Comment> getAll() {
    return commentRepo.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Comment> findById(Long id) {
    return commentRepo.findById(id);
  }

  @Override
  @Transactional
  public Comment create(InputCommentDto commentDto) {
    Optional<Post> post = postRepo.findById(commentDto.getPost());
    if (post.isEmpty()) {
      throw new IllegalArgumentException("Post with id %s not found".formatted(commentDto.getPost()));
    }
    Comment comment = new Comment();
    comment.setName(commentDto.getName());
    comment.setPost(post.get());
    commentRepo.save(comment);
    return comment;
  }

  @Override
  @Transactional
  public void delete(Comment comment) {
    commentRepo.delete(comment);
  }

  @Override
  @Transactional(readOnly = true)
  public OutPutCommentDto findCommentByUserIdAndPostId(Long userId, Long postId, Long commentId) {
    Optional<Comment> result = commentRepo.findByIdAndPost_IdAndPost_User_Id(commentId, postId,
        userId);
    if ( !result.isPresent() ) {
      throw new IllegalArgumentException("Comment not found");
    }
    return OutPutCommentDto.map(result.get());
  }
}
