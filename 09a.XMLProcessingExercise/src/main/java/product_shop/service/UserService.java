package product_shop.service;

import product_shop.domain.dto.exportDTO.UserWithSoldProductRootDTO;
import product_shop.domain.dto.exportDTO.UsersAndProductsRootDTO;
import product_shop.domain.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User getRandomUser();

    void createMultipleUsers();

    UserWithSoldProductRootDTO getUsersWithSoldProducts();

    void exportUsersWithSoldProducts();

    UsersAndProductsRootDTO getSellsByUser();

    void exportUsersAndProductsSold();
}
