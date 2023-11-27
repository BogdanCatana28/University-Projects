package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.requests.SignUpRequest;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("get_all")
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {

            return ResponseEntity.badRequest().body("No users found");
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
        Optional<User> userOptional = userService.getUserById(id);

        return userOptional.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body("User not found"));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            User createdUser = userService.createUser(signUpRequest);

            return ResponseEntity.ok(createdUser);
        } catch (ValidatorException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        try {
            User updatedUserResult = userService.updateUser(id, updatedUser);

            return ResponseEntity.ok(updatedUserResult);
        } catch (ValidatorException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);

            return ResponseEntity.ok("User deleted successfully");
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body("User not found");
        }
    }
}

