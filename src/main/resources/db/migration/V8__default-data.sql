INSERT INTO Generos VALUES 
    (DEFAULT, "Ficção Científica", now(), now()),
    (DEFAULT, "Romance", now(), now()),
    (DEFAULT, "Fantasia Medieval", now(), now()),
    (DEFAULT, "Terror", now(), now());

INSERT INTO Status VALUES 
    (DEFAULT, "Disponível", now(), now()),
    (DEFAULT, "Em manutenção", now(), now()),
    (DEFAULT, "Danificado", now(), now());

INSERT INTO Roles VALUES 
    (DEFAULT, "ROLE_CUSTOMER", 1),
    (DEFAULT, "ROLE_EMPLOYEE", 1),
    (DEFAULT, "ROLE_ADMIN", 1);