package source_files.controllers.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.responses.JwtToken;
import source_files.data.responses.TResponse;

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
