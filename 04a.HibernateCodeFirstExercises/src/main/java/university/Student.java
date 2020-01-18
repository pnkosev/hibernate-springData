//package university;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "students")
//public class Student extends Person {
//    private Double averageGrade;
//    private String attendance;
//    private Set<Course> courses;
//
//    public Student() {
//    }
//
//    public Student(int id, String firstName, String lastName, String phoneNumber) {
//        super(id, firstName, lastName, phoneNumber);
//    }
//
//    @Column(name = "average_grade")
//    public Double getAverageGrade() {
//        return this.averageGrade;
//    }
//
//    public void setAverageGrade(Double averageGrade) {
//        this.averageGrade = averageGrade;
//    }
//
//    @Column(name = "attendance")
//    public String getAttendance() {
//        return this.attendance;
//    }
//
//    public void setAttendance(String attendance) {
//        this.attendance = attendance;
//    }
//
//    @ManyToMany
//    @JoinTable(name = "students_courses",
//    joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
//    public Set<Course> getCourses() {
//        return this.courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }
//}
