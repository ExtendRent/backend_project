package source_files.controllers.userControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.userDTOs.UserDTO;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@Validated
@CrossOrigin
public class UsersController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<TResponse<List<UserDTO>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<UserDTO>>tResponseBuilder()
                .response(this.userService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<UserDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<UserDTO>tResponseBuilder()
                .response(this.userService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<UserDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<UserDTO>>tResponseBuilder()
                .response(this.userService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

}
