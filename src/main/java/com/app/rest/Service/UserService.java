package com.app.rest.Service;

import com.app.rest.DTO.UserDTO;
import com.app.rest.Model.UserModel;
import com.app.rest.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getUser(String username){
        List<UserModel> opt = userRepository.findByNameContaining(username);

        if(opt.isEmpty()){
            return null;
        }
        return opt.stream().map(e -> UserDTO.convertToUserDto(e)).collect(Collectors.toList());
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

        Optional.ofNullable(user.getName()).ifPresent(existingUser::setName);
        Optional.ofNullable(user.getBirthDate()).ifPresent(existingUser::setBirthDate);
        Optional.ofNullable(user.getPublicPlace()).ifPresent(existingUser::setPublicPlace);
        Optional.ofNullable(user.getCEP()).ifPresent(existingUser::setCEP);
        Optional.ofNullable(user.getNumber()).ifPresent(existingUser::setNumber);
        Optional.ofNullable(user.getCity()).ifPresent(existingUser::setCity);

        return userRepository.save(existingUser);
    }
}
