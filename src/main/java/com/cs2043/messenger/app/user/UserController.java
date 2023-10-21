package com.cs2043.messenger.app.user;

import com.cs2043.messenger.app.user.models.User;
import com.cs2043.messenger.app.user.models.UserCreationRequest;
import com.cs2043.messenger.app.user.models.UserLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
public record UserController(UserService userService) {
    @GetMapping(path = "/login")
    public Optional<User> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        log.info("login requested by {}", userLoginRequest);
        return userService.loginUser(userLoginRequest);
    }

    @PostMapping
    public void createUser(@RequestBody UserCreationRequest userCreationRequest) {
        log.info("new user creation {}", userCreationRequest);
        userService.createUser(userCreationRequest);
    }

    @GetMapping(path = "{id}")
    public User getUser(@PathVariable("id") Long userId) {
        log.info("user from id requested {}", userId);
        return userService.getUser(userId);
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("list of users requested");
        return userService.getAllUsers();
    }

    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) String profilePhoto,
            @RequestParam(required = false) Long branchId
    ) {
        log.info("user profile update requested {}", userId);
        userService.updateUser(
                userId,
                address,
                jobTitle,
                profilePhoto,
                branchId
        );
    }
}
