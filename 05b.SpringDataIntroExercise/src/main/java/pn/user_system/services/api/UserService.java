package pn.user_system.services.api;

import pn.user_system.models.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    List<User> getAllWithProvider(String provider);

    void save(User user);

    List<User> getAllIsDeletedTrue();

    List<User> getAllIsDeletedFalse();

    void putAllInactiveToDeletedSince(Date date);

    List<User> getAllWithinAge(int after, int before);
}
