package miu.edu.lab.repo;

import miu.edu.lab.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {}
