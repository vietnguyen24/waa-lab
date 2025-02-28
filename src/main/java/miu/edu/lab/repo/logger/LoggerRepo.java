package miu.edu.lab.repo.logger;

import miu.edu.lab.model.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepo extends JpaRepository<Logger, Long> {}
