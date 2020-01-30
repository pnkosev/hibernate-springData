package pn.models.dtos.views;

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
public class CustomerPurchaseViewDTO implements Serializable {

    private String fullName;

    private int boughtCars;

    private BigDecimal spentMoney;
}
