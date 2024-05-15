drop database if exists redata;
create database if not exists redata;
Use redata;

create table if not exists Empresa 
(idEmpresa int primary key auto_increment,
nomeEmpresa varchar(45) not null,
CNPJ char (14) not null);

create table if not exists localizacaoEmpresa
(idLocalizacaoEmpresa int primary key auto_increment,
CEP char(8) not null,
estado varchar(45),
logradouro varchar(150),
numero varchar(4),
bairro varchar(50),
complemento varchar(20),
fkEmpresa int,
	constraint fkEmpresaLocalizacao foreign key (fkEmpresa) references Empresa (idEmpresa));
    
create table if not exists Contato
(idContato int primary key auto_increment,
nome varchar(45) not null,
email varchar(45) not null, 
telefone varchar(11),
fkEmpresa int,
	constraint fkEmpresaContato foreign key(fkEmpresa) references Empresa (idEmpresa));

create table if not exists Conta
(idConta int primary key auto_increment,
login varchar(45) not null,
senha varchar(45) not null,
siglaConta char(3) not null,
dataCriacao datetime,
fkEmpresa int,
	constraint fkEmpresaTipoConta foreign key (fkEmpresa) references Empresa (idEmpresa));
    
create table if not exists Projeto (
idProjeto int primary key auto_increment,
nomeDemanda varchar(45),
quantidadeDeMaquinas int,
dataInicio datetime,
dataTermino datetime,
descricao varchar(250),
responsavel varchar(45),
fkEmpresa int,
	constraint fkEmpresaProjeto foreign key (fkEmpresa) references Empresa (idEmpresa));
        
create table if not exists Maquina
(idMaquina int primary key auto_increment,
operacao tinyint,
usuario varchar(45),
destino varchar(45),
sistemaOperacional varchar(45),
temperatura double,
tempoAtividade int,
fkProjeto int,
fkEmpresa int,
    foreign key (fkProjeto) REFERENCES Projeto (idProjeto),
    foreign key (fkEmpresa) REFERENCES Projeto (fkEmpresa)
)auto_increment = 10000;

create table if not exists DispositivosUSB
(deviceId int primary key auto_increment,
descricao varchar(45));
	
create table if not exists Blacklist
(idBlacklist int,
statusBloqueio tinyint,
motivoBloqueio varchar(250),
fkDeviceId char(50),
fkMaquina int,
fkProjeto int,
fkEmpresa int,
primary key (idBlacklist, fkDeviceId, fkMaquina, fkProjeto, fkEmpresa),
    foreign key (fkDeviceId) references DispositivosUSB (deviceId),
	foreign key (fkMaquina) references Maquina (idMaquina),
    foreign key (fkProjeto) references Projeto (idProjeto),
    foreign key (fkEmpresa) references Empresa (idEmpresa));
    
    
create table if not exists infoHardware
(codHardware int primary key auto_increment,
nomeCpu varchar(150),
memoriaTotalRam int,
nomeDisco varchar(150),
nomeRede varchar(150),
fkMaquina int,
	constraint fkMaquinaHardware foreign key (fkMaquina) references Maquina (idMaquina));
    
create table if not exists `Cpu`
(idCpu int primary key auto_increment,
frequencia double,
numeroCpuFisica int,
numeroCpuLogica int,
numeroPacote int,
usoDeCpu double,
tempoCapturas datetime,
fkCodHardware int,
	constraint fkHardwareCpu foreign key (fkCodHardware) references infoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaCpu foreign key (fkMaquina) references Maquina (idMaquina)
);
    
create table if not exists Ram
(idRam int primary key auto_increment,
memoriaDisponivel double,
memoriaEmUso double,
tempoCapturas datetime,
fkCodHardware int,
	constraint fkHardwareRam foreign key (fkCodHardware) references infoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaRam foreign key (fkMaquina) references Maquina (idMaquina)
);

create table if not exists Disco
(idDisco int primary key auto_increment,
escritas int,
bytesEscritos double,
leituras int,
bytesLidos double,
tempoCapturas datetime,
fkCodHardware int,
	constraint fkHardwareDisco foreign key (fkCodHardware) references infoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaDisco foreign key (fkMaquina) references Maquina (idMaquina)
);
    
create table if not exists Rede
(idRede int primary key auto_increment,
enderecoIPV4 varchar(15),
enderecoMac varchar(30),
pacotesEnviados int,
pacotesRecebidos int,
nomeHost varchar(45),
tempoCapturas datetime,
fkCodHardware int,
	constraint fkHardwareRede foreign key (fkCodHardware) references infoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaRede foreign key (fkMaquina) references Maquina (idMaquina)
);
    
create table if not exists Volume (
idVolume int primary key auto_increment,
fkDisco int,
    constraint fkDiscoVolume foreign key (fkDisco) references Disco (idDisco),
fkCodHardware int,
    constraint fkCodHardwareVolume foreign key (fkCodHardware) references infoHardware (codHardware),
fkMaquina int,
    constraint fkMaquinaVolume foreign key (fkMaquina) references Maquina (idMaquina),
pontoMontagem char(3),
sistemaArmazenamento char(10),
volumeDisponivel double,
volumeTotal double,
quantidadeDeVolume int,
tempoCapturas datetime
);

select * from Empresa;
select * from localizacaoEmpresa;
select * from Contato;
select * from Conta;
select * from Projeto;
select * from Maquina;
select * from infoHardware;
select * from Cpu;
select * from Ram;
select * from Disco;
select * from DispositivosUSB;

select * from Rede;

