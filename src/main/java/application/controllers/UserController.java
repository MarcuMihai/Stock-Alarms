package application.controllers;

import application.dtos.UserDTO;
import application.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOs = userService.getUsers();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> insertUser(@RequestBody UserDTO userDTO) {
        UUID userID = userService.insert(userDTO);
        if (userID==null) {
            return new ResponseEntity<>("Email address taken", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userID, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        UUID userID = userService.update(userDTO);
        if (userID==null) {
            return new ResponseEntity<>("There is no user with these characteristics", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userID, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UUID> deleteUser(@PathVariable("id") UUID id) {
        userService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") UUID id) {
        UserDTO userDTO = userService.findUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
