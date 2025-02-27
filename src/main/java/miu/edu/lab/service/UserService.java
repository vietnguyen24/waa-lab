package miu.edu.lab.service;

import miu.edu.lab.model.User;

public interface UserService {
  Iterable<User> getAll();
  User findById(Long id);
  void create(User post);
  void delete(Long id);
}
