ALTER TABLE Usuarios
DROP COLUMN genero;

ALTER TABLE Usuarios
CHANGE email login varchar(100);