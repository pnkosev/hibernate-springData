package product_shop.domain.dto.importDTO;

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

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO implements Serializable {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @NotNull(message = "Last name cannot be null!")
    @Length(min = 3, message = "Last name should be at least 3 characters!")
    @XmlAttribute(name = "last-name")
    private String lastName;

    @Min(value = 16, message = "Users must be at least 16 years old!")
    @XmlAttribute(name = "age")
    private Integer age;
}
