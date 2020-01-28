package pn.domain.dto.view;

import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductSellerDTO implements Serializable {
    @NotNull
    @Length(min = 3)
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Length(min = 3)
    @SerializedName("seller")
    private String sellerFullName;

    public ProductSellerDTO() {
    }

    public ProductSellerDTO(String name, BigDecimal price, String sellerFullName) {
        this.name = name;
        this.price = price;
        this.sellerFullName = sellerFullName;
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

    public String getSellerFullName() {
        return this.sellerFullName;
    }

    public void setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
    }
}
