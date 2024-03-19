package src.core.utilities.logger.timer;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import src.core.utilities.ResponseTimeMeasurement;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

    private boolean isEnabled = true;

    @Before("execution(* src.controller..*(..))")
    public void beforeExecution() {
        if (isEnabled) {
            ResponseTimeMeasurement.start();
        }
    }

    @After("execution(* src.controller..*(..))")
    public void afterExecution() {
        if (isEnabled()) {
            double duration = ResponseTimeMeasurement.end();
            log.info("Execution Time: {} seconds\n", duration);
        }

    }

    public void enable() {
        log.info("Time Logger is enabled");
        this.isEnabled = true;
    }

    public void disable() {
        log.info("Time Logger is disabled");
        this.isEnabled = false;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }
}


