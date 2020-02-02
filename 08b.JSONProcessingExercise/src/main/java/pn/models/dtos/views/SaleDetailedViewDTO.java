package pn.models.dtos.views;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailedViewDTO implements Serializable {

    @SerializedName("car")
    private CarViewNoIdDTO carViewNoIdDTO;

    private String customerName;

    @SerializedName("Discount")
    private Double discount;

    private BigDecimal price;

    private BigDecimal priceWithDiscount;
}
