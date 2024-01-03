package sv.library.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Generos")
@Entity(name = "Genero")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descricao", nullable = false)
    private String description;
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public Genre(String description) {
        this.description = description;
    }
}
