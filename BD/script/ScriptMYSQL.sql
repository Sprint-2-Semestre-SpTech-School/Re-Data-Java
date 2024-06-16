drop database if exists redata;
CREATE DATABASE IF NOT EXISTS redata;
USE redata;

CREATE TABLE IF NOT EXISTS `Empresa` (
  `idEmpresa` INT NOT NULL auto_increment,
  `nomeEmpresa` VARCHAR(45) NOT NULL,
  `CNPJ` CHAR(14) NOT NULL UNIQUE,
  PRIMARY KEY (`idEmpresa`)
  );
  
CREATE TABLE IF NOT EXISTS `Conta` (
  `idConta` INT NOT NULL auto_increment,
  `login` VARCHAR(45) NOT NULL UNIQUE,
  `senha` VARCHAR(45) NOT NULL,
  `siglaConta` CHAR(3) NOT NULL,
  `dataCriacao` DATETIME NOT NULL,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idConta`, `fkEmpresa`),
  CONSTRAINT `fk_Conta_Empresa1`
    FOREIGN KEY (`fkEmpresa`)
    REFERENCES `Empresa` (`idEmpresa`)
  ) auto_increment = 100;

  CREATE TABLE IF NOT EXISTS `LocalizacaoEmpresa` (
  `idLocalizacaoEmpresa` INT NOT NULL auto_increment,
  `CEP` INT(8) NOT NULL UNIQUE,
  `estado` VARCHAR(45) NULL,
  `logradouro` VARCHAR(150) NULL,
  `numero` VARCHAR(4) NULL,
  `bairro` VARCHAR(50) NULL,
  `complemento` VARCHAR(20) NULL,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idLocalizacaoEmpresa`, `fkEmpresa`),
  CONSTRAINT `fk_LocalizaçãoEmpresa_Empresa1`
    FOREIGN KEY (`fkEmpresa`)
    REFERENCES `Empresa` (`idEmpresa`)
  ) auto_increment = 200;
  
  CREATE TABLE IF NOT EXISTS `Contato` (
  `idContato` INT NOT NULL auto_increment,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL UNIQUE,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idContato`),
  CONSTRAINT `fk_Contato_Empresa1`
    FOREIGN KEY (`fkEmpresa`)
    REFERENCES `Empresa` (`idEmpresa`)
    ) auto_increment = 300;

CREATE TABLE IF NOT EXISTS `Projeto` (
  `idProjeto` INT NOT NULL auto_increment,
  `nomeDemanda` VARCHAR(45) NULL,
  `dataInicio` char(10) NULL,
  `dataTermino` char(10) NULL,
  `descricao` VARCHAR(250) NULL,
  `responsavel` VARCHAR(45) NULL,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idProjeto`, `fkEmpresa`),
  CONSTRAINT `fk_Projeto_Empresa1`
    FOREIGN KEY (`fkEmpresa`)
    REFERENCES `Empresa` (`idEmpresa`)
    ) auto_increment = 400;    

CREATE TABLE IF NOT EXISTS `Maquina` (
  `idMaquina` INT NOT NULL auto_increment,
  `usuario` VARCHAR(45) NULL,
  `sistemaOperacional` VARCHAR(45) NULL,
  `temperatura` DOUBLE NULL,
  `tempoAtividade` INT NULL,
  `destino` VARCHAR(45) NULL,
  `descricao` VARCHAR(200) NULL,
  `fkProjeto` INT NOT NULL,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idMaquina`),
  INDEX `fk_Maquina_Projeto1_idx` (`fkProjeto` ASC, `fkEmpresa` ASC) VISIBLE,
  CONSTRAINT `fk_Maquina_Projeto1`
    FOREIGN KEY (`fkProjeto` , `fkEmpresa`)
    REFERENCES `Projeto` (`idProjeto` , `fkEmpresa`)
    ) auto_increment = 500;
    
    CREATE TABLE IF NOT EXISTS `DispositivoUsb` (
	idDispositivo INT PRIMARY KEY AUTO_INCREMENT,
	idDevice CHAR(50) NOT NULL UNIQUE,
  `descricao` VARCHAR(45) NULL
	)auto_increment = 600;

CREATE TABLE IF NOT EXISTS `BlockList` (
  `idBlockList` INT NOT NULL auto_increment,
  `statusBloqueio` TINYINT NULL,
  `motivoBloqueio` VARCHAR(250) NULL,
  `fkDeviceId` INT NOT NULL,
  `fkMaquina` INT NOT NULL,
  PRIMARY KEY (`idBlockList`, `fkDeviceId`, `fkMaquina`),
  CONSTRAINT `fk_Maquina_has_DispositivosUSB_DispositivosUSB1`
    FOREIGN KEY (`fkDeviceId`)
    REFERENCES `DispositivoUsb` (`idDispositivo`),
  CONSTRAINT `fk_Blocklist_Maquina1`
    FOREIGN KEY (`fkMaquina`)
    REFERENCES `Maquina` (`idMaquina`)
) auto_increment = 700;

CREATE TABLE IF NOT EXISTS `InfoHardware` (
  `idHardware` INT NOT NULL auto_increment,
  `tipoHardware` VARCHAR(45) NULL,
  `nomeHardware` VARCHAR(150) NULL,
  `unidadeCaptacao` VARCHAR(45) NULL,
  `valorTotal` DECIMAL(10,2) NULL,
  `fkMaquina` INT NOT NULL,
  PRIMARY KEY (`idHardware`),
	CONSTRAINT `fk_InfoHardware_Maquina1`
    FOREIGN KEY (`fkMaquina`)
    REFERENCES `Maquina` (`idMaquina`)
    )auto_increment = 1000;

CREATE TABLE IF NOT EXISTS `Registro` (
  `idRegistro` INT NOT NULL auto_increment,
  `nomeRegistro` VARCHAR(250),
  `valorRegistro` DECIMAL(20,4) NULL,
  `tempoCapturas` DATETIME NULL,
  `fkHardware` INT NOT NULL,
  PRIMARY KEY (`idRegistro`, `fkHardware`),
  CONSTRAINT `fk_registro_InfoHardware1`
    FOREIGN KEY (`fkHardware`)
    REFERENCES `InfoHardware` (`idHardware`)
    )auto_increment = 10000;
    