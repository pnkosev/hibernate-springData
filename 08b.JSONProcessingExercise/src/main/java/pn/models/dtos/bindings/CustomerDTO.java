package pn.models.dtos.bindings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO implements Serializable {

    @NotNull(message = "Customer's name cannot be null!")
    @Length(min = 3, message = "Customer's name must be at least 3 characters!")
    private String name;

    @NotNull(message = "Customer's birth date cannot be null!")
    private Date birthDate;

    @NotNull(message = "Customer's information about being young driver cannot be null!")
    private boolean isYoungDriver;
}
