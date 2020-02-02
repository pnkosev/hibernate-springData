package product_shop.domain.dto.importDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDTO implements Serializable {

    @NotNull(message = "Product's name cannot be null!")
    @Length(min = 3, max = 30, message = "Product's name must be between 3 and 30 characters!")
    @XmlElement(name = "name")
    private String name;

    @NotNull
    @Min(value = 0, message = "Product's price must be a positive number!")
    @XmlElement(name = "price")
    private BigDecimal price;
}
