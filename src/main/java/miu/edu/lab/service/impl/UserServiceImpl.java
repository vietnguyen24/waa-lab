package miu.edu.lab.service.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.lab.aspect.annotation.ExecutedTime;
import miu.edu.lab.model.User;
import miu.edu.lab.repo.UserRepo;
import miu.edu.lab.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepo userRepo;
  
  @Override
  public Iterable<User> getAll() {
    return userRepo.findAll();
  }

  @Override
  @ExecutedTime
  public User findById(Long id) {
    return userRepo.findById(id).orElse(null);
  }

  @Override
  public void create(User user) {
    userRepo.save(user);
  }

  @Override
  public void delete(Long id) {
    userRepo.deleteById(id);
  }
}
