package miu.edu.lab.repo;

import miu.edu.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {}
