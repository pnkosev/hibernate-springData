package product_shop.service;

import pn.domain.dto.binding.UserDTO;
import pn.domain.dto.view.UserCountDTO;
import pn.domain.dto.view.UserProductSoldDTO;
import pn.domain.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    void createMultipleUsers(Collection<UserDTO> users);

    User getRandomUser();

    List<UserProductSoldDTO> getUsersWithSoldProducts();

    UserCountDTO getSellsByUser();
}
