//package sales;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.Set;
//
//@Entity
//@Table(name = "products")
//public class Product {
//    private int id;
//    private String name;
//    private Double quantity;
//    private BigDecimal price;
//    private Set<Sale> sales;
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public int getId() {
//        return this.id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Column(name = "name")
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Column(name = "quantity")
//    public Double getQuantity() {
//        return this.quantity;
//    }
//
//    public void setQuantity(Double quantity) {
//        this.quantity = quantity;
//    }
//
//    @Column(name = "price")
//    public BigDecimal getPrice() {
//        return this.price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    @OneToMany(mappedBy = "product", targetEntity = Sale.class)
//    public Set<Sale> getSales() {
//        return this.sales;
//    }
//
//    public void setSales(Set<Sale> sales) {
//        this.sales = sales;
//    }
//}
