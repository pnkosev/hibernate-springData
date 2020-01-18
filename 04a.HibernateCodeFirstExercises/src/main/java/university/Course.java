//package university;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.Set;
//
//@Entity
//@Table(name = "courses")
//public class Course {
//    private int id;
//    private String name;
//    private String description;
//    private Date startDate;
//    private Date endDate;
//    private int credits;
//    private Set<Student> students;
//    private Teacher teacher;
//
//    public Course() {
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
//    @Column(name = "name")
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Column(name = "description")
//    public String getDescription() {
//        return this.description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Column(name = "start_date")
//    public Date getStartDate() {
//        return this.startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    @Column(name = "end_date")
//    public Date getEndDate() {
//        return this.endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
//    @Column(name = "credits")
//    public int getCredits() {
//        return this.credits;
//    }
//
//    public void setCredits(int credits) {
//        this.credits = credits;
//    }
//
//    @ManyToMany(mappedBy = "courses", targetEntity = Student.class)
//    public Set<Student> getStudents() {
//        return this.students;
//    }
//
//    public void setStudents(Set<Student> students) {
//        this.students = students;
//    }
//
//    @ManyToOne()
//    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
//    public Teacher getTeacher() {
//        return this.teacher;
//    }
//
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
//}
