package miu.edu.lab.service;

import java.util.List;
import miu.edu.lab.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
  List<Post> getAll();
  Post findById(Long id);
  void create(Post post);
  boolean delete(Long id);
}
