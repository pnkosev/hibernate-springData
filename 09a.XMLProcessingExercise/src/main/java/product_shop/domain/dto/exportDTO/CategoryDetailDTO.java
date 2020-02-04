package product_shop.domain.dto.exportDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDetailDTO implements Serializable {
    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "products-count")
    private int productsCount;

    @XmlElement(name = "average-price")
    private Double averagePrice;

    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;
}
