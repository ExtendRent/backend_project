package src.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.data.global_responses.JwtToken;
import src.data.global_responses.TResponse;

@RestController
@RequestMapping("/api/v1/verify")
@CrossOrigin
@RequiredArgsConstructor
public class VerifyController {

    @GetMapping("/email")
    ResponseEntity<TResponse<JwtToken>> verifyEmailAddress(@RequestParam("token") String token) {

        return null;
    }
}
