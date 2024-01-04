package sv.library.api.domain;

import jakarta.persistence.*;
import lombok.*;
import sv.library.api.dto.books.CreateBookData;

import java.time.LocalDateTime;

@Table(name = "Livros")
@Entity(name = "Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo", nullable=false)
    private String title;
    @Column(name = "autor", nullable=false)
    private String author;
    @Column(name = "editora", nullable=true)
    private String publisher;
    @Column(name = "ano_lancamento", nullable=false)
    private String year;
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "ativo")
    private boolean active = true;
    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genre genre;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Book(CreateBookData data, Status status, Genre genre) {
        this.title = data.title();
        this.author = data.author();
        this.publisher = data.publisher();
        this.year = data.year();
        this.genre = genre;
        this.status = status;
    }
}
