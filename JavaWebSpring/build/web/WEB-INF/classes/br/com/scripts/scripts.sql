/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  99039833
 * Created: 21/02/2022
 */

create database modulo3_spring_mvc;

create table modulo3_spring_mvc.sexo(
	id integer not null primary key,
	descricao varchar(45) not null
);

insert into modulo3_spring_mvc.sexo (id, descricao)
values 
(1, "Masculino"),
(2, "Feminino"),
(3, "Transgênero"),
(4, "Neutro"),
(5, "Não-binário");


CREATE TABLE modulo3_spring_mvc.pessoa (
  id integer NOT NULL AUTO_INCREMENT primary key,
  nome varchar(45) NOT NULL,
  sobre_nome varchar(45) NOT NULL,
  nome_completo varchar(90) NOT NULL,
  data_nascimento date NOT NULL,
  id_sexo int not null,
  foreign key (id_sexo) references modulo3_spring_mvc.sexo(id)
  
);

CREATE TABLE modulo3_spring_mvc.tipo_usuario(
id int primary key,
descricao varchar(30) not null
);

create table modulo3_spring_mvc.usuario (
id int auto_increment primary key,	
id_pessoa int not null,
id_tipo_usuario int not null,
login varchar(45) not null,
senha varchar(45) not null,
ativo boolean not null default true,
foreign key (id_pessoa) 
references modulo3_spring_mvc.pessoa(id)
on update cascade
on delete cascade,
foreign key (id_tipo_usuario) 
references modulo3_spring_mvc.tipo_usuario(id)
);

 create unique index ind_user_log_x_pass on user(login);

