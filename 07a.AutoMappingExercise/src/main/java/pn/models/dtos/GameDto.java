package pn.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String imageURL;
    private String description;
    private LocalDate releaseDate;
}
