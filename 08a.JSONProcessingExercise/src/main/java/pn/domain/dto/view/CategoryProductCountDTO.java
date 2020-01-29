package pn.domain.dto.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class CategoryProductCountDTO implements Serializable {
    private String category;
    private int productsCount;
    private Double averagePrice;
    private BigDecimal totalRevenue;

    public CategoryProductCountDTO() {
    }

    public CategoryProductCountDTO(String category, int productsCount, Double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductsCount() {
        return this.productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return this.averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
