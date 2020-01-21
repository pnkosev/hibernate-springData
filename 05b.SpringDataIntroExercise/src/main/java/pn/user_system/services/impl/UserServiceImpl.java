package pn.user_system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pn.user_system.models.entities.User;
import pn.user_system.repositories.UserRepository;
import pn.user_system.services.api.UserService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllWithProvider(String provider) {
        return userRepository.findAllByEmailEndingWith(provider);
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public List<User> getAllIsDeletedTrue() {
        return this.userRepository.findAllByDeletedTrue();
    }

    @Override
    public List<User> getAllIsDeletedFalse() {
        return this.userRepository.findAllByDeletedFalse();
    }

    @Override
    public void putAllInactiveToDeletedSince(Date date) {
        this.userRepository.updateAllInactiveUsersSinceGivenDate(date);
    }

    @Override
    public List<User> getAllWithinAge(int after, int before) {
        return this.userRepository.findAllByAgeAfterAndAgeBefore(after, before);
    }
}
