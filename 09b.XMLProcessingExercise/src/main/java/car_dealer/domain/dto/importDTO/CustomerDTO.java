package car_dealer.domain.dto.importDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDTO implements Serializable {

    @NotNull(message = "Customer's name cannot be null!")
    @XmlAttribute(name = "name")
    private String name;

    @NotNull(message = "Customer's birth date cannot be null!")
    @XmlElement(name = "birth-date")
    private Date birthDate;

    @NotNull(message = "Customer's information about being young driver cannot be null!")
    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;
}
