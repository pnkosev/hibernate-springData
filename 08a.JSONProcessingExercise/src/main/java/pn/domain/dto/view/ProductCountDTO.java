package pn.domain.dto.view;

import java.util.Set;

public class ProductCountDTO {
    private int count;
    private Set<ProductNamePriceDTO> products;

    public ProductCountDTO() {
    }

    public ProductCountDTO(int count, Set<ProductNamePriceDTO> products) {
        this.count = count;
        this.products = products;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<ProductNamePriceDTO> getProducts() {
        return this.products;
    }

    public void setProducts(Set<ProductNamePriceDTO> products) {
        this.products = products;
    }
}
