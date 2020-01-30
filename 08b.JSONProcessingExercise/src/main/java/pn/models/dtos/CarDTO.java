package pn.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CarDTO implements Serializable {
    @NotNull(message = "Car's make cannot be null!")
    @Length(min = 3, message = "Car's make must be at least 3 characters!")
    private String make;

    @NotNull(message = "Car's model cannot be null!")
    @Length(min = 3, message = "Car's model must be at least 3 characters!")
    private String model;

    @NotNull(message = "Car's travelled distance cannot be null!")
    @Min(value = 0, message = "Car's travelled distance must be a positive number!")
    private Long travelledDistance;
}
