package car_dealer.domain.dto.importDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarDTO implements Serializable {

    @NotNull(message = "Car's make cannot be null!")
    @XmlElement(name = "make")
    private String make;

    @NotNull(message = "Car's model cannot be null!")
    @XmlElement(name = "model")
    private String model;

    @NotNull(message = "Car's travelled distance cannot be null!")
    @Min(value = 0, message = "Car's travelled distance must be positive!")
    @XmlElement(name = "travelled-distance")
    private Long travelledDistance;
}
