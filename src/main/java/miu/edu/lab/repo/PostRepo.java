package miu.edu.lab.repo;

import java.util.List;
import miu.edu.lab.model.Post;

public interface PostRepo {
  List<Post> getAll();
  Post findById(Long id);
  void create(Post post);
  void delete(Long id);
}
