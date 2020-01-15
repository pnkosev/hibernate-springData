package entities;

import java.util.Date;

public class Student {
    private int id;
    private String name;
    private Date registrationDate;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return String.format("%d. %s is registered since %s",
                this.id,
                this.name,
                this.registrationDate);
    }
}
