package src.controller.auth.verify;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import src.controller.TResponse;
import src.core.security.model.JwtToken;

import static src.controller.auth.verify.LogConstant.VERIFY_EMAIL_ADDRESS_REQUEST_RECEIVED;
import static src.controller.auth.verify.LogConstant.VERIFY_EMAIL_ADDRESS_SUCCESSFUL;

@RestController
@Slf4j
@RequestMapping("/api/v1/verify")
@RequiredArgsConstructor
public class VerifyController {

    @GetMapping("/email")
    ResponseEntity<TResponse<JwtToken>> verifyEmailAddress(@RequestParam("token") String token) {
        log.info(VERIFY_EMAIL_ADDRESS_REQUEST_RECEIVED, token);
        log.info(VERIFY_EMAIL_ADDRESS_SUCCESSFUL);
        return null;
    }
}
