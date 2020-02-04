package product_shop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.domain.dto.exportDTO.ProductNamePriceBuyerFirstLastNameDTO;
import product_shop.domain.dto.exportDTO.UserWithSoldProductDTO;
import product_shop.domain.dto.exportDTO.UserWithSoldProductRootDTO;
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

    //    @Override
//    public UserWithSoldProductRootDTO getUsersWithSoldProducts() {
//        UserWithSoldProductRootDTO userList = new UserWithSoldProductRootDTO();
//
//        List<UserWithSoldProductDTO> users = this.userRepository.findAllByProductsSold()
//                .stream()
//                .map(u -> {
//                    UserWithSoldProductDTO user = mapper.map(u, UserWithSoldProductDTO.class);
//                    for (Product product : u.getProductsSold()) {
//                        ProductNamePriceBuyerFirstLastNameDTO productDTO = this.mapper.map(product, ProductNamePriceBuyerFirstLastNameDTO.class);
//                        user.getProductsSold().add(productDTO);
//                    }
//                    return user;
//                })
//                .collect(Collectors.toList());
//
//        userList.setUsers(users);
//
//        return userList;
//    }

//    @Override
//    public UserCountDTO getSellsByUser() {
//        List<User> users = this.userRepository.findAllByProductsSold();
//
//        Set<UserProductSoldDetailedDTO> usersDetails = users
//                .stream()
//                .map(u -> {
//                    UserProductSoldDetailedDTO userDetails = mapper.map(u, UserProductSoldDetailedDTO.class);
//
//                    ProductCountDTO productCountDTO = new ProductCountDTO();
//                    productCountDTO.setCount(u.getProductsSold().size());
//
//                    Set<ProductNamePriceDTO> productsWithNameAndPrice = u.getProductsSold()
//                            .stream()
//                            .map(product -> mapper.map(product, ProductNamePriceDTO.class))
//                            .collect(Collectors.toSet());
//
//                    productCountDTO.setProducts(productsWithNameAndPrice);
//
//                    userDetails.setSoldProducts(productCountDTO);
//
//                    return userDetails;
//                })
//                .sorted((u1, u2) -> {
//                    int comp = u2.getSoldProducts().getCount() - u1.getSoldProducts().getCount();
//                    if (comp == 0) {
//                        comp = u1.getLastName().compareTo(u2.getLastName());
//                    }
//                    return comp;
//                })
//                .collect(Collectors.toCollection(LinkedHashSet::new));
//
//        UserCountDTO userCountDTO = new UserCountDTO();
//
//        userCountDTO.setCount(usersDetails.size());
//        userCountDTO.setUsers(usersDetails);
//
//        return userCountDTO;
//    }

    @Override
    public User getRandomUser() {
        return this.userRepository.getRandomEntity();
    }
}
