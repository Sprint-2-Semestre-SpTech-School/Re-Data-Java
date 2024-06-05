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
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `siglaConta` CHAR(3) NOT NULL,
  `dataCriacao` DATETIME NOT NULL,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idConta`, `fkEmpresa`),
  CONSTRAINT `fk_Conta_Empresa1`
    FOREIGN KEY (`fkEmpresa`)
    REFERENCES `Empresa` (`idEmpresa`)
  ) auto_increment = 100;

  CREATE TABLE IF NOT EXISTS `localizacaoEmpresa` (
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
  `dataInicio` DATETIME NULL,
  `dataTermino` DATETIME NULL,
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
  `usuario` VARCHAR(45) NOT NULL,
  `sistemaOperacional` VARCHAR(45) NOT NULL,
  `temperatura` DOUBLE NULL,
  `tempoAtividade` INT NULL,
  `fkProjeto` INT NOT NULL,
  `fkEmpresa` INT NOT NULL,
  PRIMARY KEY (`idMaquina`),
  INDEX `fk_Maquina_Projeto1_idx` (`fkProjeto` ASC, `fkEmpresa` ASC) VISIBLE,
  CONSTRAINT `fk_Maquina_Projeto1`
    FOREIGN KEY (`fkProjeto` , `fkEmpresa`)
    REFERENCES `Projeto` (`idProjeto` , `fkEmpresa`)
    ) auto_increment = 500;
    
    CREATE TABLE IF NOT EXISTS `dispositivoUsb` (
	idDispositivo INT PRIMARY KEY AUTO_INCREMENT,
	idDevice CHAR(50) NOT NULL UNIQUE,
  `descricao` VARCHAR(45) NULL
	)auto_increment = 600;

CREATE TABLE IF NOT EXISTS `blackList` (
  `idBlackList` INT NOT NULL auto_increment,
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
    REFERENCES `Maquina` (`idMaquina`)
    ) auto_increment = 700;

CREATE TABLE IF NOT EXISTS `infoHardware` (
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

CREATE TABLE IF NOT EXISTS `registro` (
  `idRegistro` INT NOT NULL auto_increment,
  `nomeRegistro` VARCHAR(250),
  `valorRegistro` DECIMAL(20,4) NULL,
  `tempoCapturas` DATETIME NULL,
  `fkHardware` INT NOT NULL,
  PRIMARY KEY (`idRegistro`, `fkHardware`),
  CONSTRAINT `fk_registro_InfoHardware1`
    FOREIGN KEY (`fkHardware`)
    REFERENCES `infoHardware` (`idHardware`)
    )auto_increment = 10000;
    
-- INSERT INTO Conta (login, senha, siglaConta, dataCriacao, fkEmpresa) VALUES 
-- ('sabrina', '123chris', 'FCM', NOW(),1);
    
    select last_insert_id();	
    select * from registro;
    select * from maquina;
    select * from infoHardware;
    select * from empresa;
    select * from registro;
    select * from projeto;
    select * from contato;
    select * from Conta;
    select * from localizacaoEmpresa;
    select idEmpresa from Empresa;
    SELECT * FROM projeto WHERE idProjeto = ? AND fkEmpresa = ?;
    
    select idRegistro, nomeRegistro, valorRegistro, tempoCapturas, idProjeto, idMaquina from registro 
		join infoHardware on fkHardware = idHardware
        join maquina on fkMaquina = idMaquina
        join projeto on fkProjeto = idProjeto
        where idProjeto = 400 and nomeRegistro = "usoRam" and valorRegistro >= 15;
        
SELECT idRegistro, nomeRegistro, tempoCapturas, idProjeto, idMaquina, AVG(valorRegistro) AS mediaDados
FROM registro 
JOIN infoHardware ON fkHardware = idHardware
JOIN maquina ON fkMaquina = idMaquina
JOIN projeto ON fkProjeto = idProjeto
WHERE idProjeto = 400 
GROUP BY idRegistro, nomeRegistro, tempoCapturas, idProjeto, idMaquina;

 SELECT idMaquina, COUNT(idRegistro) as totalCapturas from registro
 join infoHardware on fkHardware = idHardware
 join maquina on fkMaquina = idMaquina
 where nomeHardware = 'Ram' and valorRegistro > 4 group by idMaquina order by registro desc limit 1;
 
 select idMaquina, count(idRegistro) as totalCapturas from registro
 join infoHardware on fkHardware = idHardware
 join maquina on fkMaquina = idMaquina 
 where tipoHardware = 'Cpu' or 'Ram' and valorRegistro >= 70 and fkProjeto = 400
 group by idMaquina order by totalCapturas desc limit 1;
 
 
 SELECT
    idMaquina,
    SUM(CASE WHEN tipoHardware = 'Cpu' AND valorRegistro >= 5 THEN 1 ELSE 0 END) AS totalCapturasCpu,
    SUM(CASE WHEN tipoHardware = 'Ram' AND valorRegistro >= 1 THEN 1 ELSE 0 END) AS totalCapturasRam,
    SUM(CASE WHEN tipoHardware = 'Disco' AND valorRegistro >= 1 THEN 1 ELSE 0 END) AS totalCapturasDisco,
    SUM(CASE WHEN tipoHardware = 'Rede' AND valorRegistro >= 1 THEN 1 ELSE 0 END) AS totalCapturasRede
FROM
    registro
JOIN infoHardware ON fkHardware = idHardware
JOIN maquina ON fkMaquina = idMaquina
GROUP BY
    idMaquina
ORDER BY
    totalCapturasCpu DESC,
    totalCapturasRam DESC,
    totalCapturasDisco DESC,
    totalCapturasRede DESC
LIMIT 1;


select idMaquina, count(idRegistro) as totalCapturas from registro
        join infoHardware on fkHardware = idHardware
        join maquina on fkMaquina = idMaquina 
        where tipoHardware = 'Cpu' and valorRegistro >= 5 and fkProjeto = 400
        group by idMaquina order by totalCapturas desc limit 1;
        
        
select count(idRegistro) as eventos_criticos,
DATE_FORMAT(tempoCapturas, '%Y-%m-%d: %H:%i:%s') AS intervalo_tempo,
max(valorRegistro) as maior_valor
from registro 
join infoHardware on fkHardware = idHardware
JOIN maquina ON fkMaquina = idMaquina
join projeto on fkProjeto = idProjeto
where tempoCapturas >= NOW() - interval 20 second and valorRegistro >= 1 and tipoHardware = "Cpu" and nomeRegistro = "usoCpu" and fkProjeto = 400
group by DATE_FORMAT(tempoCapturas, '%Y-%m-%d: %H:%i:%s') order by maior_valor desc limit 1;

select * from registro WHERE nomeRegistro = "bytesEscrita" and valorRegistro <= 1;


SELECT COUNT(*) AS eventos_criticos,
max(valorRegistro) as maior_valor
FROM registro
JOIN infoHardware ON fkHardware = idHardware
JOIN maquina ON fkMaquina = idMaquina
JOIN projeto ON fkProjeto = idProjeto
WHERE tempoCapturas >= NOW() - INTERVAL 20 SECOND
  AND valorRegistro >= 5
  AND tipoHardware = "Cpu"
  AND nomeRegistro = "usoCpu"
  AND fkProjeto = 400;
  
  
  
