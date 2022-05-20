package org.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsersList() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            "application/vnd.example.todo_payload+json"})
    public ResponseEntity<User> addUser(@RequestBody UserOnload userFromRequest) {
        User user = userService.addUser(userFromRequest);
        return ResponseEntity.ok(user);
    }

}
