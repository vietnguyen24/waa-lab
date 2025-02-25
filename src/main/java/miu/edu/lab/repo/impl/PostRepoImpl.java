package miu.edu.lab.repo.impl;

import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import miu.edu.lab.model.Post;
import miu.edu.lab.repo.PostRepo;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepoImpl implements PostRepo {
  private static final List<Post> posts = 
      new ArrayList<>(List.of(
              new Post(1L, "Post 1", "Content 1", "Author 1"),
              new Post(2L, "Post 2", "Content 2", "Author 2"),
              new Post(3L, "Post 3", "Content 3", "Author 3")));

  @Override
  public List<Post> getAll() {
    return posts;
  }

  @Override
  @Nullable
  public Post findById(Long id) {
    return posts.stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
  }

  @Override
  public void create(Post post) {
    posts.add(post);
  }

  @Override
  public void delete(Long id) {
    posts.removeIf(post -> post.getId().equals(id));
  }
}
