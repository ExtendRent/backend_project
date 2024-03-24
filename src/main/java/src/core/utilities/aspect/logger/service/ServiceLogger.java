package src.core.utilities.aspect.logger.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceLogger {
    private boolean isEnabled = true;

    @Before("execution(* src.service..*(..))")
    public void logServiceMethods(JoinPoint joinPoint) {
        if (isEnabled) {
            String methodName = joinPoint.getSignature().getName();
            String logMessage = switch (methodName) {
                case "fix" -> ServiceLogConstant.FIXING_REQUEST_MESSAGE;
                case "check" -> ServiceLogConstant.CHECKING_REQUEST_MESSAGE;
                case "checkDataList" -> ServiceLogConstant.CHECKING_DATA_LIST_MESSAGE;
                case "softDelete" -> ServiceLogConstant.SOFT_DELETING_MESSAGE;
                case "delete" -> ServiceLogConstant.DELETING_MESSAGE;
                default -> null;
            };
            if (logMessage != null) {
                log.info(logMessage);
            }
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

    public void isEnabledInfo() {
        log.info("Service logger status: " + isEnabled);
    }

    public boolean isEnabled() {
        return (isEnabled);
    }
}
