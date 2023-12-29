package sv.library.api.domain;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "Usuarios")
@Entity(name = "Usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "nome", nullable = false)
    private String Name;
    @Column(name = "email", nullable = false)
    private String Email;
    @Column(name = "hash_senha", nullable = false)
    private String Password;
    @Column(name = "genero", nullable = true)
    private String gender;
    @OneToMany(mappedBy = "user")
    private List<Book> books;

}
