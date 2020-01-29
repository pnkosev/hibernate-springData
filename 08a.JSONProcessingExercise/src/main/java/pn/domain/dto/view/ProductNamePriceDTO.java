package pn.domain.dto.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductNamePriceDTO implements Serializable {
    private String name;
    private BigDecimal price;

    public ProductNamePriceDTO() {
    }

    public ProductNamePriceDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
