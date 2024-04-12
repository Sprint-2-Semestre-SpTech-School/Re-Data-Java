create database ReData;
Use ReData;
drop database ReData;

create table Empresa 
(idEmpresa int primary key auto_increment,
nomeEmpresa varchar(45) not null,
CNPJ int (14) not null);

create table localizacaoEmpresa
(idLocalizacaoEmpresa int primary key auto_increment,
CEP char(8) not null,
endereco varchar(150),
numero int(4),
bairro varchar(45),
complemento varchar(20),
fkEmpresa int,
	constraint fkEmpresaLocalizacao foreign key (fkEmpresa) references Empresa (idEmpresa));
    
create table contato
(idContato int primary key auto_increment,
nome varchar(45) not null,
email varchar(45) not null, 
telefone int(11),
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
    
create table Maquina
(idMaquina int primary key auto_increment,
usuario varchar(45),
destino varchar(45),
sistemaOperacional varchar(45),
fkEmpresa int,
	constraint fkEmpresaMaquina foreign key (fkEmpresa) references Empresa (idEmpresa));
    
create table DispositivosUsb
(deviceId char(50) primary key,
descricao varchar(45));

create table Blacklist
(idBlacklist int,
fkMaquina int,
fkEmpresa int,
fkDeviceId char(50),
primary key (idBlacklist, fkMaquina, fkEmpresa, fkDeviceId),
	foreign key (fkMaquina) references Maquina (idMaquina),
    foreign key (fkEmpresa) references Empresa (idEmpresa),
    foreign key (fkDeviceId) references DispositivosUsb (deviceId));
    
create table InfoHardware
(codHardware int primary key auto_increment,
nomeCpu varchar(45),
nomeRam varchar(45),
nomeDisco varchar(45),
nomeRede varchar(45),
fkMaquina int,
	constraint fkMaquinaHardware foreign key (fkMaquina) references Maquina (idMaquina));
    
create table `Cpu`
(idCpu int primary key auto_increment,
fkCodHardware int,
	constraint fkHardwareCpu foreign key (fkCodHardware) references InfoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaCpu foreign key (fkMaquina) references InfoHardware (codHardware));
    
create table Ram
(idRam int primary key auto_increment,
fkCodHardware int,
	constraint fkHardwareRam foreign key (fkCodHardware) references InfoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaRam foreign key (fkMaquina) references InfoHardware (codHardware));

create table Disco
(idDisco int primary key auto_increment,
fkCodHardware int,
	constraint fkHardwareDisco foreign key (fkCodHardware) references InfoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaDisco foreign key (fkMaquina) references InfoHardware (codHardware));
    
create table Rede
(idRede int primary key auto_increment,
fkCodHardware int,
	constraint fkHardwareRede foreign key (fkCodHardware) references InfoHardware (codHardware),
fkMaquina int,
	constraint fkMaquinaRede foreign key (fkMaquina) references InfoHardware (codHardware));
    


