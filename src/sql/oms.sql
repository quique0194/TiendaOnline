CREATE TABLE usuario
(
   id bigserial, 
   first_name character varying(256),
   last_name character varying(256),
   username character varying(64),
   password character varying(256),
   email character varying(64),
   CONSTRAINT pk_usuario_id PRIMARY KEY (id)
);

create table producto (
	id bigserial primary key,
	name character varying(256)
);

create table compra
(
   id bigserial primary key,
   producto_id bigint,
   usuario_id  bigint,
   fecha date,
   constraint fk_producto_id foreign key (producto_id) references producto(id),
   constraint fk_usuario_id foreign key (usuario_id) references usuario(id)
);