package com.app.rest.Service;

import com.app.rest.DTO.UserDTO;
import com.app.rest.Model.UserModel;
import com.app.rest.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public UserDTO getUser(String username){
        List<UserModel> opt = userRepository.findByNameContaining(username);
        if(opt.isEmpty()){
            return null;
        }
        return UserDTO.convertToUserDto(opt.get(0));
    }

    public UserDTO removeUser(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return new UserDTO(user.get());
        }
        return null;
    }
}
