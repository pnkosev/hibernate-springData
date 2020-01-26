package pn.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.models.dtos.UserLoginDto;
import pn.models.dtos.UserRegisterDto;
import pn.models.entities.Role;
import pn.models.entities.User;
import pn.repositories.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private String loggedInUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        this.loggedInUser = "";
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();

        if (!userRegisterDto.isPasswordMatching()) {
            return sb.append("Passwords don't match!").toString();
        }

        User user = this.modelMapper.map(userRegisterDto, User.class);

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                return sb.append(violation.getMessage()).append(System.lineSeparator()).toString();
            }
        }


        if (userRepository.count() == 0) {
            user.setRole(Role.ADMIN);
        } else  {
            User userCheck = userRepository.findByEmail(user.getEmail()).orElse(null);

            if (userCheck != null) {
                return sb.append("Email address is already taken!").toString();
            }
            user.setRole(Role.USER);
        }

        sb.append(String.format("%s was registered successfully!", user.getFullName()));
        userRepository.saveAndFlush(user);


        return sb.toString();
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        StringBuilder sb = new StringBuilder();

        if (!this.loggedInUser.isEmpty()) {
            return sb.append("A user is already logged in!").toString();
        }

        User user = this.userRepository.findByEmail(userLoginDto.getEmail()).orElse(null);

        if (user == null || !user.getPassword().equals(userLoginDto.getPassword())) {
            return sb.append("Incorrect email or/and password!").toString();
        }

        this.loggedInUser = user.getEmail();
        return sb.append(String.format("Successfully logged in %s!", user.getFullName())).toString();
    }

    @Override
    public String logout() {
        StringBuilder sb = new StringBuilder();

        if (this.loggedInUser.isEmpty()) {
            return sb.append("Cannot log out. No user is logged in!").toString();
        }

        User user = this.userRepository.findByEmail(this.loggedInUser).orElse(null);
        sb.append(String.format("User %s successfully logged out!", user.getFullName()));
        this.loggedInUser = "";

        return sb.toString();
    }

    @Override
    public String getLoggedInUser() {
        return this.loggedInUser;
    }
}
