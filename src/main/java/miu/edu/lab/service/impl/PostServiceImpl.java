package miu.edu.lab.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import miu.edu.lab.dto.InputPostDto;
import miu.edu.lab.model.Post;
import miu.edu.lab.model.User;
import miu.edu.lab.repo.PostRepo;
import miu.edu.lab.repo.UserRepo;
import miu.edu.lab.service.PostService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepo postRepo;
  private final UserRepo userRepo;
  
  @Override
  public Iterable<Post> getAll() {
    return postRepo.findAll();
  }

  @Override
  public Post findById(Long id) {
    return postRepo.findById(id).orElse(null);
  }

  @Override
  public Post create(InputPostDto postDto) {
    Optional<User> user = userRepo.findById(postDto.userId());
    if (user.isEmpty()) {
      throw new IllegalArgumentException("User with id %s not found".formatted(postDto.userId()));
    }
    Post post = new Post();
    post.setTitle(postDto.title());
    post.setUser(user.get());
    post.setContent(postDto.content());
    postRepo.save(post);
    return post;
  }

  @Override
  public void delete(Long id) {
    postRepo.deleteById(id);
  }

  @Override
  public List<User> findUserByPostTitle(String title) {
    return postRepo.findUserByPostTitle(title);
  }
}
