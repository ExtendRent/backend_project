package source_files.controllers.securityControllers;

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

    //TODO verify email endpointi.
    @GetMapping("/email")
    ResponseEntity<TResponse<JwtToken>> verifyEmailAddress(@RequestParam("token") String token) {

        return null;
    }
}
