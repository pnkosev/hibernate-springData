package product_shop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ValidatorUtils validatorUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, ValidatorUtils validatorUtils, Validator validator) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validatorUtils = validatorUtils;
    }

    @Override
    public void createMultipleUsers(Collection<UserDTO> userDTOs) {
        if (this.userRepository.count() == 0) {

            for (UserDTO userDTO : userDTOs) {
                if (!this.validatorUtils.isValid(userDTO)) {
                    this.validatorUtils.getViolations(userDTO).forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }

                User user = mapper.map(userDTO, User.class);
                this.userRepository.save(user);
            }

            List<User> users = this.userRepository.findAll();

            for (int i = 0; i < users.size() * 2; i++) {
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

    @Override
    public List<UserProductSoldDTO> getUsersWithSoldProducts() {
        return this.userRepository.findAllByProductsSold()
                .stream()
                .map(u -> mapper.map(u, UserProductSoldDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserCountDTO getSellsByUser() {
        List<User> users = this.userRepository.findAllByProductsSold();

        Set<UserProductSoldDetailedDTO> usersDetails = users
                .stream()
                .map(u -> {
                    UserProductSoldDetailedDTO userDetails = mapper.map(u, UserProductSoldDetailedDTO.class);

                    ProductCountDTO productCountDTO = new ProductCountDTO();
                    productCountDTO.setCount(u.getProductsSold().size());

                    Set<ProductNamePriceDTO> productsWithNameAndPrice = u.getProductsSold()
                            .stream()
                            .map(product -> mapper.map(product, ProductNamePriceDTO.class))
                            .collect(Collectors.toSet());

                    productCountDTO.setProducts(productsWithNameAndPrice);

                    userDetails.setSoldProducts(productCountDTO);

                    return userDetails;
                })
                .sorted((u1, u2) -> {
                    int comp = u2.getSoldProducts().getCount() - u1.getSoldProducts().getCount();
                    if (comp == 0) {
                        comp = u1.getLastName().compareTo(u2.getLastName());
                    }
                    return comp;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));

        UserCountDTO userCountDTO = new UserCountDTO();

        userCountDTO.setCount(usersDetails.size());
        userCountDTO.setUsers(usersDetails);

        return userCountDTO;
    }
}
