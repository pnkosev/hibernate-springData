package hospital;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BasicEntity {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Date dateOfBirth;
    private String picture;
    private boolean isInsured;
    private Set<Visitation> visitations;
    private Set<Diagnose> diagnoses;
    private Set<Medication> medications;

    public Patient() {
    }

    public Patient(int id, String firstName, String lastName, String address, String email, Date dateOfBirth, String picture, boolean isInsured) {
        super(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setEmail(email);
        this.setDateOfBirth(dateOfBirth);
        this.setPicture(picture);
        this.setInsured(isInsured);
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "picture")
    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Column(name = "is_insured")
    public boolean isInsured() {
        return this.isInsured;
    }

    public void setInsured(boolean insured) {
        isInsured = insured;
    }

    @ManyToMany
    @JoinTable(name = "patients_visitations",
    joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "visitation_id", referencedColumnName = "id"))
    public Set<Visitation> getVisitations() {
        return this.visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    @ManyToMany
    @JoinTable(name = "patients_diagnoses",
    joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"))
    public Set<Diagnose> getDiagnoses() {
        return this.diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @ManyToMany
    @JoinTable(name = "patients_medications",
    joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"))
    public Set<Medication> getMedications() {
        return this.medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }
}
