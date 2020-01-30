package pn.models.dtos.views;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SaleViewDTO implements Serializable {
    @SerializedName("Discount")
    private Double discount;

    @SerializedName("Car")
    private CarViewDTO car;
}
