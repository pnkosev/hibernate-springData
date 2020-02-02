package product_shop.service;

import product_shop.domain.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User getRandomUser();

    void createMultipleUsers();

//    List<UserProductSoldDTO> getUsersWithSoldProducts();
//
//    UserCountDTO getSellsByUser();
}
