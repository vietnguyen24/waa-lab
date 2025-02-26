package miu.edu.lab.repo;

import miu.edu.lab.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Long> {
}
