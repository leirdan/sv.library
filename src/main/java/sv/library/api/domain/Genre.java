package sv.library.api.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Generos")
@Entity(name = "Genero")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "descricao", nullable = false)
    private String description;
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "genre")
    private List<Book> books;
}
