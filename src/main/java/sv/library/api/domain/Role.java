package sv.library.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sv.library.api.utils.RoleName;

@Table(name="roles")
@Entity(name="Role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Column(name="ativo")
    private boolean active = true;

}
