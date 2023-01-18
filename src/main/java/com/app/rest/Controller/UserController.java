package com.app.rest.Controller;

import com.app.rest.DTO.UserDTO;
import com.app.rest.Model.UserModel;
import com.app.rest.Repository.UserRepository;
import com.app.rest.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/all")
    @Operation(summary = "This method return all users in database")
    public List<UserModel> listUsers(){
        return userRepository.findAll();
    }

    @GetMapping
    @Operation(summary = "This method search user in database by name")
    public List<UserDTO> getUserByParam(@RequestParam String username){
        List<UserDTO> user = userService.getUser(username);
        if (user == null){
            return new ArrayList<>();
        }
        return user;
    }

    @PostMapping
    @Operation(summary = "This method create a new User in application")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        UserModel savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "This method change some user information ")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        UserModel updatedUser = userService.update(id, user);
        return new ResponseEntity<UserModel>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "This method deletes an existing user")
    public ResponseEntity<?> removeUser(@PathVariable Long id){
        UserDTO remove = userService.removeUser(id);
        if(remove == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return ResponseEntity.ok().build();
    }
}
