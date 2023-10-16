package application.builders;

import application.dtos.UserDTO;
import application.entities.User;

public class UserBuilder {

    private UserBuilder() {
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getAlarms());
    }

    public static User toEntity(UserDTO userDTO) {
        return new User(userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getAlarms());
    }
}
