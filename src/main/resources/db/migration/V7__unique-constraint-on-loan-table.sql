alter table Emprestimos
add constraint UC_Emprestimos unique (livro_id, usuario_id, ativo);