package miu.edu.lab.aspect;

import miu.edu.lab.repo.UserRepo;
import static org.junit.jupiter.api.Assertions.assertTrue;

import miu.edu.lab.model.User;
import miu.edu.lab.repo.logger.LoggerRepo;
import miu.edu.lab.service.CurrentUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AspectTest {
  @Autowired private UserRepo userRepo;

  @Autowired private LoggerRepo loggerRepo;

  @Test
  void testSaveLogger() {
    User user = new User();
    user.setName("test");
    userRepo.save(user);

    User currentUser = CurrentUserService.getCurrentUser();

    assertTrue(
        loggerRepo.findAll().stream()
            .anyMatch(logger -> logger.getOperation().equals("save User User(id=1, name=testUser)") &&
                                logger.getPrinciple().getName().equals(currentUser.getName())));
  }

  @Test
  void testDeleteLogger() {
    User user = new User();
    user.setName("test");
    userRepo.save(user);
    userRepo.delete(user);

    User currentUser = CurrentUserService.getCurrentUser();

    assertTrue(
        loggerRepo.findAll().stream()
            .anyMatch(logger -> logger.getOperation().equals("delete User User(id=2, name=test)") &&
                logger.getPrinciple().getName().equals(currentUser.getName())));
  }
}
