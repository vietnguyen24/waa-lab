package miu.edu.lab.repo;

import java.util.List;
import miu.edu.lab.model.Post;
import miu.edu.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepo extends JpaRepository<Post, Long> {
  // find  users by given post title
  @Query("SELECT distinct p.user FROM Post p WHERE p.title like %:title%")
  List<User> findUserByPostTitle(String title);
}
