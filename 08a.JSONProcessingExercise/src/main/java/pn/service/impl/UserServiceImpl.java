package pn.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.domain.dto.binding.UserDTO;
import pn.domain.entity.User;
import pn.repository.UserRepository;
import pn.service.UserService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void createMultipleUsers(Collection<UserDTO> userDTOs) {
        if (this.userRepository.count() == 0) {
            User[] users = mapper.map(userDTOs, User[].class);
            this.userRepository.saveAll(Arrays.asList(users));

            for (int i = 0; i < users.length * 2; i++) {
                User randomUser = this.getRandomUser();
                User randomFriend = this.getRandomUser();

                if (randomUser != null && randomFriend != null && !randomUser.equals(randomFriend)) {
                    randomUser.getFriends().add(randomFriend);
                    randomFriend.getFriends().add(randomUser);
                }
            }
        }
    }

    @Override
    public User getRandomUser() {
        return this.userRepository.getRandomEntity();
    }
}
