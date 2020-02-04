package car_dealer.domain.dto.importDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartDTO implements Serializable {
    @NotNull(message = "Part's name cannot be null!")
    @Length(min = 3, message = "Part's name must be at least 3 characters!")
    @XmlAttribute(name = "name")
    private String name;

    @NotNull(message = "Part's price cannot be null!")
    @Min(value = 0, message = "Price must be a positive number!")
    @XmlAttribute(name = "price")
    private BigDecimal price;

    @NotNull(message = "Part's quantity cannot be null!")
    @Min(value = 0, message = "Part's quantity cannot be a negative number!")
    @XmlAttribute(name = "quantity")
    private int quantity;
}
