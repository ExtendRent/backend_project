package src.core.utilities;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Before("execution(* src.controller..*(..))")
    public void beforeExecution() {
        ResponseTimeMeasurement.start();
    }

    @After("execution(* src.controller..*(..))")
    public void afterExecution() {
        double duration = ResponseTimeMeasurement.end();
        logger.info("Execution Time: {} seconds\n", duration);
    }
}


