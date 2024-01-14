CREATE TABLE Emprestimos(
    id bigint not null auto_increment primary key,
    livro_id bigint not null,
    usuario_id bigint not null,
    data_vencimento datetime not null,
    criado_em datetime not null,
    devolvido tinyint(1),
    ativo tinyint(1),

    constraint fk_emprestimos_livro_id foreign key(livro_id) references Livros(id),
    constraint fk_emprestimos_usuario_id foreign key(usuario_id) references Usuarios(id)
);