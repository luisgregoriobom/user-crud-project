package com.app.rest.Controller;

import com.app.rest.DTO.UserDTO;
import com.app.rest.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id){
        UserDTO remove = userService.removeUser(id);
        if(remove == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return ResponseEntity.ok().build();
    }



}
