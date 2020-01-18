package hospital;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicEntity {
    private int id;

    protected BasicEntity() {
    }

    protected BasicEntity(int id) {
        this.setId(id);
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
