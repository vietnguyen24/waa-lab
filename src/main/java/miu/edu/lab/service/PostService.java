package miu.edu.lab.service;

import miu.edu.lab.model.Post;

public interface PostService {
  Iterable<Post> getAll();
  Post findById(Long id);
  void create(Post post);
  void delete(Long id);
}
