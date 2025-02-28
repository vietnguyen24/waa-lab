package miu.edu.lab.repo;

import java.util.Optional;
import miu.edu.lab.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
  Optional<Comment> findByIdAndPost_IdAndPost_User_Id(Long id, Long userId, Long postId);
}
