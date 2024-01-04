CREATE TABLE Roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ativo TINYINT(1) NOT NULL
);

CREATE TABLE UsuariosRoles (
    usuario_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY(usuario_id, role_id),
    CONSTRAINT UsuariosRoles_usuarioId_fk FOREIGN KEY (usuario_id) REFERENCES Usuarios (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UsuariosRoles_roleId_fk FOREIGN KEY (role_id) REFERENCES Roles (id) ON DELETE CASCADE ON UPDATE CASCADE
)
