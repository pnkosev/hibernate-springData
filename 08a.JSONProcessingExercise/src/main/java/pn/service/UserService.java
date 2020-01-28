package pn.service;

import pn.domain.dto.binding.UserDTO;
import pn.domain.entity.User;

import java.util.Collection;

public interface UserService {
    void createMultipleUsers(Collection<UserDTO> users);
    User getRandomUser();
}
