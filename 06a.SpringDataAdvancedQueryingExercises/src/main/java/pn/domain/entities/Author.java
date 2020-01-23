package pn.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
@NamedStoredProcedureQuery(
        name = "usp_get_book_count_by_author",
        procedureName = "usp_get_book_count_by_author",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "first_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "last_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = Integer.class)
        })
public class Author extends BaseEntity {

    private String firstName;
    private String lastName;
    private Set<Book> books;

    public Author() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "author", targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
