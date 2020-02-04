package product_shop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.domain.dto.exportDTO.*;
import product_shop.domain.dto.importDTO.UserDTO;
import product_shop.domain.dto.importDTO.UserRootDTO;
import product_shop.domain.entity.Product;
import product_shop.domain.entity.User;
import product_shop.repository.UserRepository;
import product_shop.service.UserService;
import product_shop.util.ValidatorUtil;
import product_shop.util.XMLParser;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private static final String USERS_IMPORT_PATH = "src/main/resources/xml/input/users.xml";
    private static final String USERS_WITH_SALES_EXPORT_PATH = "src/main/resources/xml/output/users-with-sales.xml";
    private static final String USERS_AND_PRODUCTS_EXPORT_PATH = "src/main/resources/xml/output/users-and-products.xml";

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ValidatorUtil validatorUtil;
    private final XMLParser xmlParser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, ValidatorUtil validatorUtil, Validator validator, XMLParser xmlParser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public User getRandomUser() {
        return this.userRepository.getRandomEntity();
    }

    @Override
    public void createMultipleUsers() {
        if (this.userRepository.count() == 0) {
            UserRootDTO userRootDTO = this.xmlParser.fromXML(UserRootDTO.class, USERS_IMPORT_PATH);

            for (UserDTO userDTO : userRootDTO.getUsers()) {
                if (!this.validatorUtil.isValid(userDTO)) {
                    this.validatorUtil.getViolations(userDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                User user = this.mapper.map(userDTO, User.class);

                userRepository.save(user);
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
    public UserWithSoldProductRootDTO getUsersWithSoldProducts() {
        UserWithSoldProductRootDTO users = new UserWithSoldProductRootDTO();

        List<UserWithSoldProductDTO> userList = this.userRepository.findAllByProductsSold()
                .stream()
                .map(u -> {
                    UserWithSoldProductDTO userWithSoldProductDTO = this.mapper.map(u, UserWithSoldProductDTO.class);
                    List<ProductNamePriceBuyerFirstLastNameDTO> products = userWithSoldProductDTO
                            .getProductsSold()
                            .stream()
                            .filter(f -> f.getBuyerLastName() != null)
                            .collect(Collectors.toList());
                    userWithSoldProductDTO.setProductsSold(products);
                    return userWithSoldProductDTO;
                })
                .collect(Collectors.toList());

        users.setUsers(userList);

        return users;
    }

    @Override
    public void exportUsersWithSoldProducts() {
        this.xmlParser.toXML(this.getUsersWithSoldProducts(), USERS_WITH_SALES_EXPORT_PATH);
    }

    @Override
    public UsersAndProductsRootDTO getSellsByUser() {
        List<UserFirstLastNameAgeDTO> userFirstLastNameAgeDTOs = this.userRepository.findAllByProductsSold()
                .stream()
                .map(u -> {
                    UserFirstLastNameAgeDTO userDTO = this.mapper.map(u, UserFirstLastNameAgeDTO.class);

                    List<ProductNamePriceDTO> productNamePriceDTOs = u.getProductsSold()
                            .stream()
                            .filter(p -> p.getBuyer() != null)
                            .map(p -> this.mapper.map(p, ProductNamePriceDTO.class))
                            .collect(Collectors.toList());

                    SoldProductsDTO soldProductsDTO = new SoldProductsDTO();
                    soldProductsDTO.setSoldProducts(productNamePriceDTOs);
                    soldProductsDTO.setCount(productNamePriceDTOs.size());
                    userDTO.setSoldProducts(soldProductsDTO);
                    return userDTO;
                })
                .sorted((u1, u2) -> {
                    int comp = Integer.compare(u2.getSoldProducts().getCount(), u1.getSoldProducts().getCount());
                    if (comp == 0) {
                        comp = u1.getLastName().compareTo(u2.getLastName());
                    }
                    return comp;
                })
                .collect(Collectors.toList());

        UsersAndProductsRootDTO usersAndProductsRootDTO = new UsersAndProductsRootDTO();

        usersAndProductsRootDTO.setUsers(userFirstLastNameAgeDTOs);
        usersAndProductsRootDTO.setCount(userFirstLastNameAgeDTOs.size());

        return usersAndProductsRootDTO;
    }

    @Override
    public void exportUsersAndProductsSold() {
        this.xmlParser.toXML(this.getSellsByUser(), USERS_AND_PRODUCTS_EXPORT_PATH);
    }
}
