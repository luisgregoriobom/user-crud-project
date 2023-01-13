package Service;

import DTO.UserDTO;
import Model.UserModel;
import Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public UserDTO getUserByName(String username){
        Optional<UserModel> usernameOptional = userRepository.findByName(username);
        return usernameOptional.map(UserDTO::convertToUserDto).orElse(null);
    }
}
