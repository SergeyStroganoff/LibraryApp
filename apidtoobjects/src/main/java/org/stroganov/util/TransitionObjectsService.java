package org.stroganov.util;

import org.stroganov.entities.User;
import org.stroganov.models.UserDTO;

public class TransitionObjectsService {

    public static User getUser(UserDTO userDTO) {
        return new User(userDTO.getUserID(), userDTO.getFullName(), userDTO.getLogin(),
                userDTO.getPasscodeHash(), userDTO.isBlocked(), userDTO.isAdmin());
    }

}
