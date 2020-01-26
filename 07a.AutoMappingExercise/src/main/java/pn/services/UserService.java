package pn.services;

import pn.models.dtos.UserLoginDto;
import pn.models.dtos.UserRegisterDto;

public interface UserService {
    String registerUser(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);

    String logout();

    String getLoggedInUser();
}
