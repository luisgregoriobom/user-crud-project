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

    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    public UserModel update(Long id, UserModel user) {
        UserModel existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id.toString()));
        existingUser.setName(user.getName());
        existingUser.setBirthDate(user.getBirthDate());
        existingUser.setPublicPlace(user.getPublicPlace());
        existingUser.setCEP(user.getCEP());
        existingUser.setNumber(user.getNumber());
        existingUser.setCity(user.getCity());
        return userRepository.save(existingUser);
    }
}
