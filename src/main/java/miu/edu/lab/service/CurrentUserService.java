package miu.edu.lab.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import miu.edu.lab.model.User;
import miu.edu.lab.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
  private final UserRepo userRepo;
  private static User currentUser = new User("testUser");
  
  @PostConstruct
  public void init() {
    userRepo.save(currentUser);
  }

  public static User getCurrentUser() {
    return currentUser;
  }
  
 
}
