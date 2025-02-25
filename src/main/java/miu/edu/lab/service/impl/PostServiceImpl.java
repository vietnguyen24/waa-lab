package miu.edu.lab.service.impl;

import java.util.List;
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
  public List<Post> getAll() {
    return postRepo.getAll();
  }

  @Override
  public Post findById(Long id) {
    return postRepo.findById(id);
  }

  @Override
  public void create(Post post) {
    postRepo.create(post);

  }

  @Override
  public boolean delete(Long id) {
    if (postRepo.findById(id) == null) return false;
    postRepo.delete(id);
    return true;
  }
}
