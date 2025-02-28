package miu.edu.lab.aspect;

import lombok.RequiredArgsConstructor;
import miu.edu.lab.service.CurrentUserService;
import miu.edu.lab.service.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAspect {
  private final LoggerService loggerService;
  
  @Pointcut("execution(* miu.edu.lab.repo.*.save(..))")
  public void commonSave() {
  }

  @Pointcut("execution(* miu.edu.lab.repo.*.delete(..))")
  public void commonDelete() {
  }

  @After("commonSave() || commonDelete()")
  void logAfterSave(JoinPoint joinPoint) {
    String operation = joinPoint.getSignature().getName() + " " +joinPoint.getArgs()[0].getClass().getSimpleName() + " " + joinPoint.getArgs()[0].toString();
    loggerService.create(new miu.edu.lab.model.Logger(CurrentUserService.getCurrentUser(), operation));
  }
  
  @Around("@annotation(miu.edu.lab.aspect.annotation.ExecutedTime)")
  void logExecutedTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    joinPoint.proceed();
    long end = System.currentTimeMillis();
    System.out.println("Execution time: " + (end - start) + "ms");
  }

}
