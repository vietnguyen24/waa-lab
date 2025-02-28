package miu.edu.lab.service;

import java.util.List;
import miu.edu.lab.dto.InputPostDto;
import miu.edu.lab.model.Post;
import miu.edu.lab.model.User;

public interface PostService {
  Iterable<Post> getAll();
  Post findById(Long id);
  Post create(InputPostDto post);
  void delete(Long id);
  List<User> findUserByPostTitle(String title);
}
