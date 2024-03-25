package src.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.user.response.UserResponse;
import src.core.rest.BaseController;
import src.service.user.UserService;

import java.util.List;

import static src.controller.user.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<TResponse<Page<UserResponse>>> getAllUsers(Pageable pageable) {
        log.info(GETTING_ALL_USERS);
        Page<UserResponse> response = userService.getAll(pageable);
        log.info(RETRIEVED_ALL_USERS, response.getTotalElements());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<UserResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(GETTING_USERS_BY_DELETED_STATE, isDeleted);
        List<UserResponse> response = userService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_USERS_BY_DELETED_STATE, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<UserResponse>> getById(@PathVariable int id) {
        log.info(GETTING_USER_BY_ID, id);
        UserResponse response = userService.getById(id);
        log.info(RETRIEVED_USER_BY_ID, id);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        log.info(GETTING_USER_COUNT_BY_DELETED_STATE, isDeleted);
        int response = userService.getCountByDeletedState(isDeleted);
        log.info(RETRIEVED_USER_COUNT_BY_DELETED_STATE, response);
        return answer(response, HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<Void> updatePassword(
            @RequestParam int id, @RequestParam String password) {
        log.info(UPDATING_USER_PASSWORD, id);
        userService.updatePassword(id, password);
        log.info(USER_PASSWORD_UPDATED, id);
        return answer(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/block/{id}")
    public ResponseEntity<Void> blockUser(@PathVariable int id) {
        log.info(BLOCKING_USER, id);
        userService.blockUser(id);
        log.info(USER_BLOCKED, id);
        return answer(HttpStatus.NO_CONTENT);
    }
}
