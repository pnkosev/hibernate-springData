//package sales;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "customers")
//public class Customer {
//    private int id;
//    private String name;
//    private String email;
//    private String creditCardNumber;
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
//    @Column(name = "email")
//    public String getEmail() {
//        return this.email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Column(name = "credit_card_number")
//    public String getCreditCardNumber() {
//        return this.creditCardNumber;
//    }
//
//    public void setCreditCardNumber(String creditCardNumber) {
//        this.creditCardNumber = creditCardNumber;
//    }
//
//    @OneToMany(mappedBy = "customer", targetEntity = Sale.class)
//    public Set<Sale> getSales() {
//        return this.sales;
//    }
//
//    public void setSales(Set<Sale> sales) {
//        this.sales = sales;
//    }
//}
