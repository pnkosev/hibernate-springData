package pn.domain.dto.view;

import java.util.Set;

public class UserProductSoldDetailedDTO {
    private String firstName;
    private String lastName;
    private String age;
    private ProductCountDTO soldProducts;

    public UserProductSoldDetailedDTO() {
    }

    public UserProductSoldDetailedDTO(String firstName, String lastName, String age, ProductCountDTO soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ProductCountDTO getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(ProductCountDTO soldProducts) {
        this.soldProducts = soldProducts;
    }
}
