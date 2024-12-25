package alter.alter_core.controller;

import alter.alter_core.domain.User;
import alter.alter_core.dto.ResultDTO;
import alter.alter_core.dto.UserDTO;
import alter.alter_core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        return ResponseEntity.ok(ResultDTO.success(201, "User create successfully", user));
    }
}
