package entities.labels;

import entities.shampoo.BasicShampoo;
import interfaces.Label;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel implements Label {
    private long id;
    private String title;
    private String subTitle;
    private BasicShampoo basicShampoo;

    public BasicLabel(){

    }

    public BasicLabel(String title, String subTitle) {
        this.setTitle(title);
        this.setSubtitle(subTitle);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Override
    public String getSubtitle() {
        return this.subTitle;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subTitle = subtitle;
    }

    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class, cascade = CascadeType.ALL)
    public BasicShampoo getBasicShampoo() {
        return this.basicShampoo;
    }

    public void setBasicShampoo(BasicShampoo basicShampoo) {
        this.basicShampoo = basicShampoo;
    }
}
