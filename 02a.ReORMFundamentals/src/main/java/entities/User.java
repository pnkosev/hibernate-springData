package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.sql.Date;

@Entity
public class User {
    @Id
    private int id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private int age;
    @Column
    private Date registration_date;

    public User(String username, String password, int age, Date registration_date) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.registration_date = registration_date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return this.registration_date;
    }

    public void setRegistrationDate(Date registration_date) {
        this.registration_date = registration_date;
    }
}
