create database opv;

create table opv.secteur(
	id int not null primary key auto_increment,
    nom varchar(50)
);

create table opv.enterprise (
	id INT not null primary key auto_increment,
	nom VARCHAR(50),
	address VARCHAR(50),
    email varchar(50),
	num_tel VARCHAR(50),
	prix_titre DECIMAL(7,2),
    secteur int not null,
    constraint ent_fk_1 foreign key(secteur) references opv.secteur(id)
);

create table opv.titre (
	id varchar(50) primary key not null,
	owner INT not null,
	buyer INT,
    constraint titre_fk_1 foreign key(owner) references opv.enterprise(id),
    constraint titre_fk_2 foreign key(buyer) references opv.enterprise(id)
);

