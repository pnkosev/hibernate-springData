//package bills_payment;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "users")
//public class Owner {
//    private int id;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//    private Set<BaseBillingDetail> billingDetails;
//
//    public Owner() {
//    }
//
//    public Owner(String firstName, String lastName, String email, String password, Set<BaseBillingDetail> billingDetails) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.billingDetails = billingDetails;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public int getId() {
//        return this.id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Column(name = "first_name")
//    public String getFirstName() {
//        return this.firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    @Column(name = "last_name")
//    public String getLastName() {
//        return this.lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
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
//    @Column(name = "password")
//    public String getPassword() {
//        return this.password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @OneToMany(mappedBy = "owner")
//    public Set<BaseBillingDetail> getBillingDetails() {
//        return this.billingDetails;
//    }
//
//    public void setBillingDetails(Set<BaseBillingDetail> billingDetails) {
//        this.billingDetails = billingDetails;
//    }
//}
