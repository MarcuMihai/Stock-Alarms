package application.services;

import application.builders.UserBuilder;
import application.dtos.UserDTO;
import application.entities.User;
import application.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream()
                .map(UserBuilder::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            LOGGER.error("User with id {} was not found in db", id);
            return null;
        }
        return UserBuilder.toDTO(userOptional.get());
    }

    public UUID insert(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            LOGGER.error("User with email {} already exists in db", userDTO.getEmail());
            return null;
        }
        User user = UserBuilder.toEntity(userDTO);
        user = userRepository.save(user);
        LOGGER.debug("User with id {} was inserted in db", user.getId());
        return user.getId();
    }

    public UUID update(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) == null) {
            LOGGER.error("User with email {} does not exist in db", userDTO.getEmail());
            return null;
        }
        User user = UserBuilder.toEntity(userDTO);
        user = userRepository.save(user);
        LOGGER.debug("User with id {} was updated in db", user.getId());
        return user.getId();
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
        LOGGER.debug("User with id {} was deleted from db", id);
    }
}
