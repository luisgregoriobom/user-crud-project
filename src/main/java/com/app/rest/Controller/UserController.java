package com.app.rest.Controller;

import com.app.rest.DTO.UserDTO;
import com.app.rest.Model.UserModel;
import com.app.rest.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("/username")
    public ResponseEntity<UserDTO> getUserByParam(@RequestParam String username){
        UserDTO name = userService.getUser(username);
        if(name == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        UserModel savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        UserModel updatedUser = userService.update(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id){
        UserDTO remove = userService.removeUser(id);
        if(remove == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return ResponseEntity.ok().build();
    }



}
