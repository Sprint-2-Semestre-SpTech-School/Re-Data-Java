drop database if exists redata;
CREATE DATABASE IF NOT EXISTS redata;
USE redata;

CREATE TABLE IF NOT EXISTS `empresa` (
  `idEmpresa` INT NOT NULL,
  `nomeEmpresa` VARCHAR(45) NOT NULL,
  `CNPJ` CHAR(14) NOT NULL UNIQUE,
  PRIMARY KEY (`idEmpresa`)
  );
  
CREATE TABLE IF NOT EXISTS `conta` (
  `idConta` INT NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `siglaConta` CHAR(3) NOT NULL,
  `dataCriacao` DATETIME NOT NULL,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idConta`, `fkEmpresa`),
  CONSTRAINT `fk_Conta_Empresa1`
    FOREIGN KEY (`fkEmpresa`)
    REFERENCES `empresa` (`idEmpresa`)
  );

  CREATE TABLE IF NOT EXISTS `localizacaoEmpresa` (
  `idLocalizacaoEmpresa` INT NOT NULL,
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
    REFERENCES `empresa` (`idEmpresa`)
  );
  
  CREATE TABLE IF NOT EXISTS `contato` (
  `idContato` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefone` INT(11) NOT NULL UNIQUE,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idContato`),
  CONSTRAINT `fk_Contato_Empresa1`
    FOREIGN KEY (`fkEmpresa`)
    REFERENCES `empresa` (`idEmpresa`)
    );

CREATE TABLE IF NOT EXISTS `projeto` (
  `idProjeto` INT NOT NULL,
  `nomeDemanda` VARCHAR(45) NULL,
  `dataInicio` DATETIME NULL,
  `dataTermino` DATETIME NULL,
  `descricao` VARCHAR(250) NULL,
  `responsavel` VARCHAR(45) NULL,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idProjeto`, `fkEmpresa`),
  CONSTRAINT `fk_Projeto_Empresa1`
    FOREIGN KEY (`fkEmpresa`)
    REFERENCES `empresa` (`idEmpresa`)
    );

CREATE TABLE IF NOT EXISTS `maquina` (
  `idMaquina` INT NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `sistemaOperacional` VARCHAR(45) NOT NULL,
  `temperatura` DOUBLE NULL,
  `tempoAtividade` INT NULL,
  `fkProjeto` INT NOT NULL,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idMaquina`, `fkProjeto`, `fkEmpresa`),
  INDEX `fk_Maquina_Projeto1_idx` (`fkProjeto` ASC, `fkEmpresa` ASC) VISIBLE,
  CONSTRAINT `fk_Maquina_Projeto1`
    FOREIGN KEY (`fkProjeto` , `fkEmpresa`)
    REFERENCES `projeto` (`idProjeto` , `fkEmpresa`)
    );
    
    CREATE TABLE IF NOT EXISTS `dispositivoUsb` (
	idDispositivo INT PRIMARY KEY AUTO_INCREMENT,
	idDevice CHAR(50) NOT NULL UNIQUE,
  `descricao` VARCHAR(45) NULL
	);

CREATE TABLE IF NOT EXISTS `blackList` (
  `idBlackList` INT NOT NULL,
  `statusBloqueio` TINYINT NOT NULL,
  `motivoBloqueio` VARCHAR(250) NULL,
  `fkDeviceId` INT NOT NULL,
  `fkMaquina` INT NOT NULL,
  PRIMARY KEY (`idBlackList`, `fkDeviceId`, `fkMaquina`),
  CONSTRAINT `fk_Maquina_has_DispositivosUSB_DispositivosUSB1`
    FOREIGN KEY (`fkDeviceId`)
    REFERENCES `dispositivoUsb` (`idDispositivo`),
  CONSTRAINT `fk_Blacklist_Maquina1`
    FOREIGN KEY (`fkMaquina`)
    REFERENCES `maquina` (`idMaquina`)
    );

CREATE TABLE IF NOT EXISTS `infoHardware` (
  `idHardware` INT NOT NULL,
  `tipoHardware` VARCHAR(45) NULL,
  `nomeHardware` VARCHAR(150) NULL,
  `unidadeCaptacao` VARCHAR(45) NULL,
  `valorTotal` DECIMAL(10,2) NULL,
  `fkMaquina` INT NOT NULL,
  PRIMARY KEY (`idHardware`),
	CONSTRAINT `fk_InfoHardware_Maquina1`
    FOREIGN KEY (`fkMaquina`)
    REFERENCES `maquina` (`idMaquina`)
    );

CREATE TABLE IF NOT EXISTS `registro` (
  `idRegistro` INT NOT NULL,
  `valorRegistro` DECIMAL(10,2) NULL,
  `tempoCapturas` DATETIME NULL,
  `fkHardware` INT NOT NULL,
  PRIMARY KEY (`idRegistro`, `fkHardware`),
  CONSTRAINT `fk_registro_InfoHardware1`
    FOREIGN KEY (`fkHardware`)
    REFERENCES `infoHardware` (`idHardware`)
    );
    
    select last_insert_id();	
    select * from registro;
    select * from maquina;
    select * from infoHardware;
    select * from registro;
    select * from projeto;