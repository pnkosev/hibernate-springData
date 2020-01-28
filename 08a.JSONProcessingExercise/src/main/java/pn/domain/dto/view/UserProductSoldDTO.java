package pn.domain.dto.view;

import java.util.Set;

public class UserProductSoldDTO {
    private String firstName;
    private String lastName;
    private Set<ProductNamePriceBuyerDTO> soldProducts;

    public UserProductSoldDTO() {
    }

    public UserProductSoldDTO(String firstName, String lastName, Set<ProductNamePriceBuyerDTO> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Set<ProductNamePriceBuyerDTO> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<ProductNamePriceBuyerDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
