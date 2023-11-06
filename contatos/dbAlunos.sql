create database dbAlunos;
use dbAlunos;

create table tb_alunos (
nome varchar(255) not null,
materia varchar(255) not null,
curso varchar(255) not null,
email varchar(255) primary key
);

insert into tb_alunos(nome, materia, curso ,email) values
("Giulia","MVC","Sistemas para Internet", "giulia@iftm.com");


select nome, materia, curso, email from tb_alunos;

