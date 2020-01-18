//package university;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;
//
//@MappedSuperclass
//public abstract class Person {
//    private int id;
//    private String firstName;
//    private String lastName;
//    private String phoneNumber;
//
//    protected Person() { }
//
//    protected Person(int id, String firstName, String lastName, String phoneNumber) {
//        this.setId(id);
//        this.setFirstName(firstName);
//        this.setLastName(lastName);
//        this.setPhoneNumber(phoneNumber);
//    }
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
//    @Column(name = "phone_number")
//    public String getPhoneNumber() {
//        return this.phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//}
