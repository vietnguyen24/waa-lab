package miu.edu.lab.service.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.lab.model.Post;
import miu.edu.lab.repo.PostRepo;
import miu.edu.lab.service.PostService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepo postRepo;
  
  @Override
  public Iterable<Post> getAll() {
    return postRepo.findAll();
  }

  @Override
  public Post findById(Long id) {
    return postRepo.findById(id).orElse(null);
  }

  @Override
  public void create(Post post) {
    postRepo.save(post);

  }

  @Override
  public void delete(Long id) {
    postRepo.deleteById(id);
  }
}
