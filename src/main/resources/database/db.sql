CREATE TABLE Articulo(
	id int auto_increment,
	nombre varchar(200) not null,
	descripcion varchar(500) null,
	precioDetal int not null,
	precioMayorista int not null,
	CONSTRAINT articulo_pk primary key (id)
);
CREATE TABLE Categoria(
	id int auto_increment,
	padre varchar(20) null,
	hija varchar(20) null,
	CONSTRAINT categoria_pk primary key (id)
);
CREATE TABLE articate(
	articulo int not null,
	categoria int not null,
	CONSTRAINT articate_pk primary key(articulo, categoria),
	CONSTRAINT articate_fk foreign key (articulo) references Articulo (id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT articate_fk_1 foreign key (categoria) references Categoria (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Talla(
	id bigint auto_increment,
	articulo int not null,
	talla varchar(5) not null,
	cantidad int default 0,
	CONSTRAINT talla_pk primary key (id),
	CONSTRAINT talla_fk foreign key (articulo) references Articulo (id) on DELETE CASCADE on UPDATE CASCADE
);
CREATE TABLE Cliente(
	cedula varchar(20) not null,
	nombres varchar(100) not null,
	apellidos varchar(150) null,
	telefono varchar(10) null,
	fijo varchar(10) null,
	descripcion varchar(500) null,
	CONSTRAINT cliente_pk primary key (cedula)
);
CREATE TABLE Venta(
	id bigint auto_increment,
	fecha datetime not null,
	cliente varchar(20) not null,
	CONSTRAINT venta_pk primary key (id),
	CONSTRAINT venta_fk foreign key (cliente) references Cliente (cedula) on DELETE no action on UPDATE no action
);
CREATE TABLE venttall(
	venta bigint not null,
	talla bigint not null,
	cantidad int not null,
	CONSTRAINT venttall_pk primary key (venta, talla),
	CONSTRAINT venttall_fk foreign key (venta) references Venta (id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT venttall_fk_1 foreign key (talla) references Talla (id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO Cliente (cedula, nombres, apellidos, telefono, fijo, descripcion, mayorista) VALUES('0000', 'Consumidor', 'Final', '1234', '5678', 'Cliente esporadico, no recurrente', 0);
ALTER TABLE MoDarly.venttall ADD precioFinal INT NULL;
ALTER TABLE MoDarly.Cliente ADD mayorista BOOL NULL;
ALTER TABLE MoDarly.Venta ADD pagacon INT NULL;
ALTER TABLE MoDarly.Venta ADD vueltos INT NULL;
ALTER TABLE MoDarly.Cliente ADD fechaCreacion DATE NULL;