package sv.library.api.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Emprestimos")
@Entity(name = "Emprestimo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "livro_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
    @JoinColumn(name = "usuario_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "data_vencimento")
    private LocalDateTime expirationDate;
    @Column(name = "criado_em")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "devolvido")
    private boolean returned = false;
    @Column(name = "ativo")
    private boolean active = true;
}
