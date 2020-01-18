//package university;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "teachers")
//public class Teacher extends Person {
//    private String email;
//    private Double salaryPerHour;
//    private Set<Course> courses;
//
//    public Teacher() {
//    }
//
//    public Teacher(int id, String firstName, String lastName, String phoneNumber) {
//        super(id, firstName, lastName, phoneNumber);
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
//    @Column(name = "salary_per_hour")
//    public Double getSalaryPerHour() {
//        return this.salaryPerHour;
//    }
//
//    public void setSalaryPerHour(Double salaryPerHour) {
//        this.salaryPerHour = salaryPerHour;
//    }
//
//    @OneToMany(mappedBy = "teacher",
//            targetEntity = Course.class,
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    public Set<Course> getCourses() {
//        return this.courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }
//}
