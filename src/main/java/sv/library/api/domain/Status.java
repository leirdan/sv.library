package sv.library.api.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descricao", nullable=false)
    private String description;
    @OneToMany(mappedBy = "status")
    private List<Book> books;
}