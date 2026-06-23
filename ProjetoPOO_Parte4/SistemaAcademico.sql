CREATE DATABASE sistemaAcademico;

USE sistemaAcademico;

CREATE TABLE Pessoa (
	idPessoa int primary key auto_increment,
    cpf varchar(14),
    nome VARCHAR(100) NOT NULL,
    idade int not null,
	dataNascimento date not null,
    telefone varchar(15),
    email varchar(100),
    estadoCivil varchar(20),
    estado varchar (50),
    cidade varchar(50),
    endereco varchar(100)
);


CREATE TABLE Curso(
	cdCurso int primary key auto_increment,
    nomeCurso varchar (50) NOT NULL
); 

CREATE TABLE Aluno (
	ra varchar(6) primary key UNIQUE NOT NULL,
    status varchar(20),
	idcurso_fk int NOT NULL,
    idPessoa_fk int NOT NULL UNIQUE,
    FOREIGN KEY (idcurso_fk) references Curso(cdCurso),
    FOREIGN KEY (idpessoa_fk) references Pessoa(idPessoa)
);

CREATE TABLE Professor (
	formacao varchar(100) not null,
    especializacao varchar(100) not null,
    valorHora double not null,
    horasSemanais int not null,
    idPessoa_fk int NOT NULL UNIQUE,
    FOREIGN KEY (idpessoa_fk) references Pessoa(idPessoa)
);

drop table pessoa;
INSERT INTO curso (nomeCurso) VALUES ('Análise e Desenvolvimento de Sistemas');

SELECT p.idPessoa, p.nome, p.cpf, p.idade, p.dataNascimento, p.telefone, p.email, p.estadoCivil, p.estado, p.cidade, p.endereco, a.ra, a.status, c.nomecurso, c.cdcurso
FROM pessoa p
INNER JOIN aluno a
ON p.idpessoa = a.idpessoa_fk
INNER JOIN curso c
ON c.cdcurso = a.idcurso_fk;

SELECT p.idPessoa, p.nome, p.cpf, p.idade, p.dataNascimento, p.telefone, p.email, p.estadoCivil, p.estado, p.cidade, p.endereco, pr.formacao, pr.especializacao, pr.valorHora, pr.horasSemanais FROM pessoa p INNER JOIN professor pr ON p.idpessoa = pr.idpessoa_fk;