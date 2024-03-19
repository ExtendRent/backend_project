package src.core.utilities.logger.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static src.core.utilities.logger.service.ServiceLogConstant.*;

@Aspect
@Component
@Slf4j
public class ServiceLogger {
    private boolean isEnabled = true;

    @Before("execution(* src.service..*(..)) &&" +
            "(execution(* fix(..)) || " +
            "execution(* check(..)) || " +
            "execution(* checkDataList(..)) || " +
            "execution(* softDelete(..)) || " +
            "execution(* delete(..)))")
    public void logServiceMethods(JoinPoint joinPoint) {
        if (isEnabled) {
            String methodName = joinPoint.getSignature().getName();
            String logMessage = switch (methodName) {
                case "fix" -> FIXING_REQUEST_MESSAGE;
                case "check" -> CHECKING_REQUEST_MESSAGE;
                case "checkDataList" -> CHECKING_DATA_LIST_MESSAGE;
                case "softDelete" -> SOFT_DELETING_MESSAGE;
                case "delete" -> DELETING_MESSAGE;
                default -> "";
            };
            log.info(logMessage);
        }
    }

    public void enable() {
        log.info("Service Logger is enabled");
        this.isEnabled = true;
    }

    public void disable() {
        log.info("Service Logger is disabled");
        this.isEnabled = false;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }
}
