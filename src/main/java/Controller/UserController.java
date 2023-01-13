package Controller;

import DTO.UserDTO;
import Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> getUserByName(@RequestParam String username){
        UserDTO name = userService.getUserByName(username);
        if (name == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Username not found");
        }
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

}