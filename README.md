# Prueba-nexos
## Scripts BD
CREATE TABLE cargos
(
 cargos_id serial NOT NULL,
 nombre    varchar(50) NOT NULL,
 CONSTRAINT PK_cargos PRIMARY KEY ( cargos_id )
);
CREATE TABLE mercancía
(
 mercancia_id  serial NOT NULL,
 nombre        varchar(50) NOT NULL,
 cantidad      integer NOT NULL,
 fecha_ingreso date NOT NULL,
 total         integer NOT NULL,
 producto_id   integer NOT NULL,
 usuario_id    integer NOT NULL,
 CONSTRAINT PK_mercancía PRIMARY KEY ( mercancia_id ),
 CONSTRAINT FK_36 FOREIGN KEY ( producto_id ) REFERENCES producto ( producto_id ),
 CONSTRAINT FK_48 FOREIGN KEY ( usuario_id ) REFERENCES usuarios ( usuario_id )
);

CREATE INDEX fkIdx_37 ON mercancía
(
 producto_id
);

CREATE INDEX fkIdx_49 ON mercancía
(
 usuario_id
);

CREATE TABLE producto
(
 producto_id serial NOT NULL,
 nombre      varchar(50) NOT NULL,
 precio      integer NOT NULL,
 detalle     varchar(50) NOT NULL,
 codigo      varchar(50) NOT NULL,
 CONSTRAINT PK_producto PRIMARY KEY ( producto_id )
);

CREATE TABLE usuario_modifica
(
 usuario_modifica_id serial NOT NULL,
 fecha_modificacion  date NOT NULL,
 usuario_id          integer NOT NULL,
 mercancia_id        integer NOT NULL,
 CONSTRAINT PK_usuariomodifica PRIMARY KEY ( usuario_modifica_id ),
 CONSTRAINT FK_55 FOREIGN KEY ( usuario_id ) REFERENCES usuarios ( usuario_id ),
 CONSTRAINT FK_58 FOREIGN KEY ( mercancia_id ) REFERENCES mercancía ( mercancia_id )
);

CREATE INDEX fkIdx_56 ON usuario_modifica
(
 usuario_id
);

CREATE INDEX fkIdx_59 ON usuario_modifica
(
 mercancia_id
);
CREATE TABLE usuarios
(
 usuario_id    serial NOT NULL,
 nombre        varchar(50) NOT NULL,
 edad          integer NOT NULL,
 fecha_ingreso date NOT NULL,
 cargos_id     integer NOT NULL,
 CONSTRAINT PK_usuarios PRIMARY KEY ( usuario_id ),
 CONSTRAINT FK_18 FOREIGN KEY ( cargos_id ) REFERENCES cargos ( cargos_id )
);

CREATE INDEX fkIdx_19 ON usuarios
(
 cargos_id
);


INSERT INTO cargos (nombre) values ('Asesor de ventas')
INSERT INTO cargos (nombre) values ('administrador')
INSERT INTO cargos (nombre) values ('soporte')
INSERT INTO usuarios(
nombre, edad, fecha_ingreso, cargos_id)
VALUES ('Juan Pablo Orozco', 21,CURRENT_DATE, 3);
INSERT INTO usuarios(
nombre, edad, fecha_ingreso, cargos_id)
VALUES ('Jose Fernando', 28,CURRENT_DATE, 2);
INSERT INTO usuarios(
nombre, edad, fecha_ingreso, cargos_id)
VALUES ('Francisco Javier', 24,CURRENT_DATE, 1);
