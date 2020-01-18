package pn.springdataintro.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pn.springdataintro.models.User;
import pn.springdataintro.repositories.UserRepository;

@Service
@Primary
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        if (this.userRepository.getByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("User with that username already exists");
        }

        this.userRepository.save(user);
    }
}
