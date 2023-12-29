package sv.library.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descricao", nullable=false)
    private String description;
    @Column(name = "criado_em")
    private LocalDateTime createdAt = LocalDateTime.now();
    @OneToMany(mappedBy = "status")
    private List<Book> books;

    public Status(String description) {
        this.description = description;
    }
}