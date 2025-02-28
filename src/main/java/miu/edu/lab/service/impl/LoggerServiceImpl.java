package miu.edu.lab.service.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.lab.model.Logger;
import miu.edu.lab.model.User;
import miu.edu.lab.repo.logger.LoggerRepo;
import miu.edu.lab.service.LoggerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggerServiceImpl implements LoggerService {
  private final LoggerRepo loggerRepo;

  @Override
  public void create(Logger logger) {
    loggerRepo.save(logger);
  }
}
