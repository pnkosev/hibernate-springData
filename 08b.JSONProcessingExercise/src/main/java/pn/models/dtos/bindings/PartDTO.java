package pn.models.dtos.bindings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PartDTO implements Serializable {

    @NotNull(message = "Part's name cannot be null!")
    @Length(min = 3, message = "Part's name must be at least 3 characters!")
    private String name;

    @NotNull(message = "Part's price cannot be null!")
    @Min(value = 0, message = "Part's price must be a positive number!")
    private BigDecimal price;

    @NotNull(message = "Part's quantity cannot be null!")
    @Min(value = 0, message = "Part's quantity must be a positive number!")
    private int quantity;
}
