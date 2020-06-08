create database crud001;

use crud001;

 

create table cargo(
id_cargo int auto_increment primary key,
nome varchar(40) not null, 
descricao varchar(80)
);

 


create table funcionario (
   CPF varchar(14) not null primary key, 
   nome varchar(60), 
   dt_nascimento date, 
   email varchar(80) ,  
   id_cargo int, 
   telefone varchar(20),
   foreign key (id_cargo) references cargo(id_cargo)
);

 

create table endereco (
  id_end int auto_increment primary key, 
  rua varchar(60) not null, 
  complemento varchar(60), 
  numero int, 
  bairro varchar(60), 
  cidade varchar(60), 
  estado varchar(50), 
  CPF varchar(14) not null,
  foreign key (CPF) references funcionario(CPF));
  




  
