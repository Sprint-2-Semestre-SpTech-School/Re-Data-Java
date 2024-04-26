create database ReData;
Use ReData;
drop database ReData;

select * from empresa;
select * from localizacaoEmpresa;
select * from contato;
select * from conta;
select * from projeto;
select * from maquina;
select * from infoHardware;

select * from Cpu;

create table Empresa 
(idEmpresa int primary key auto_increment,
nomeEmpresa varchar(45) not null,
CNPJ char (14) not null);

create table localizacaoEmpresa
(idLocalizacaoEmpresa int primary key auto_increment,
CEP char(8) not null,
estado varchar(45),
logradouro varchar(150),
numero varchar(4),
bairro varchar(50),
complemento varchar(20),
fkEmpresa int,
	constraint fkEmpresaLocalizacao foreign key (fkEmpresa) references Empresa (idEmpresa));
    
create table Contato
(idContato int primary key auto_increment,
nome varchar(45) not null,
email varchar(45) not null, 
telefone varchar(11),
fkEmpresa int,
	constraint fkEmpresaContato foreign key(fkEmpresa) references Empresa (idEmpresa));

create table Conta
(idConta int primary key auto_increment,
login varchar(45) not null,
senha varchar(45) not null,
siglaConta char(3) not null,
dataCriacao datetime,
fkEmpresa int,
	constraint fkEmpresaTipoConta foreign key (fkEmpresa) references Empresa (idEmpresa));
    
create table Projeto (
idProjeto int primary key auto_increment,
nomeDemanda varchar(45),
quantidadeDeMaquinas int,
dataInicio datetime,
dataTermino datetime,
descricao varchar(250),
responsavel varchar(45),
fkEmpresa int,
	constraint fkEmpresaProjeto foreign key (fkEmpresa) references Empresa (idEmpresa));
        
create table Maquina
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
);

select * from Maquina;
select * from Projeto;

create table DispositivosUsb
(deviceId char(50) primary key,
descricao varchar(45));

create table Blacklist
(idBlacklist int,
statusBloqueio tinyint,
motivoBloqueio varchar(250),
fkDeviceId char(50),
fkMaquina int,
fkProjeto int,
fkEmpresa int,
primary key (idBlacklist, fkDeviceId, fkMaquina, fkProjeto, fkEmpresa),
    foreign key (fkDeviceId) references DispositivosUsb (deviceId),
	foreign key (fkMaquina) references Maquina (idMaquina),
    foreign key (fkProjeto) references Projeto (idProjeto),
    foreign key (fkEmpresa) references Empresa (idEmpresa));
    
select * from Blacklist;
drop table Blacklist;
    
create table InfoHardware
(codHardware int primary key auto_increment,
nomeCpu varchar(45),
memoriaTotalRam int,
nomeDisco varchar(45),
nomeRede varchar(45),
fkMaquina int,
	constraint fkMaquinaHardware foreign key (fkMaquina) references Maquina (idMaquina));
    
select * from InfoHardware;
    
create table Cpu
(idCpu char primary key,
fkCodHardware int,
	constraint fkHardwareCpu foreign key (fkCodHardware) references InfoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaCpu foreign key (fkMaquina) references Maquina (idMaquina),
frequencia double,
numeroCpuFisica int,
numeroCpuLogica int,
numeroPacote int,
usoDeCpu double,
tempoCapturas datetime
);
    
select * from Cpu;
    
create table Ram
(idRam int primary key auto_increment,
fkCodHardware int,
	constraint fkHardwareRam foreign key (fkCodHardware) references InfoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaRam foreign key (fkMaquina) references Maquina (idMaquina),
memoriaDisponivel double,
memoriaEmUso double,
tempoCapturas datetime
);

create table Disco
(idDisco int primary key auto_increment,
fkCodHardware int,
	constraint fkHardwareDisco foreign key (fkCodHardware) references InfoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaDisco foreign key (fkMaquina) references Maquina (idMaquina),
escritas int,
bytesEscritos double,
leituras int,
bytesLidos double,
tempoCapturas datetime
);
    
create table Rede
(idRede int primary key auto_increment,
fkCodHardware int,
	constraint fkHardwareRede foreign key (fkCodHardware) references InfoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaRede foreign key (fkMaquina) references Maquina (idMaquina),
enderecoIPV4 varchar(15),
enderecoMac varchar(30),
pacotesEnviados int,
pacotesRecebidos int,
redeDNS varchar(30),
nomeHost varchar(45),
tempoCapturas datetime
);
    
create table Volume (
idVolume int primary key auto_increment,

fkDisco int,
    constraint fkDiscoVolume foreign key (fkDisco) references Disco (idDisco),
fkCodHardware int,
    constraint fkCodHardwareVolume foreign key (fkCodHardware) references InfoHardware (codHardware),
fkMaquina int,
    constraint fkMaquinaVolume foreign key (fkMaquina) references Maquina (idMaquina),
pontoMontagem char(3),
sistemaArmazenamento char(10),
volumeDisponivel double,
volumeTotal double,
quantidadeDeVolume int,
tempoCapturas datetime
);

select * from Volume;
