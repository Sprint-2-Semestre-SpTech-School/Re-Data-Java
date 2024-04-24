#!/bin/bash

echo "Bem vindo ao instalador Java!!"
echo "Desenvolvido pela Re;Data para e SOMENTE PARA uso de clientes contratantes dos serviços de monitoramento"

java -version 

if [ $? == 0 ]; #Aqui ele vai perguntar o valor de retorno do comando de cima (que vai pergunta a vsersão do Java)
	then
		echo "JAVA INSTALADO!" #toda vez que vir um (echo) é porque ele vai jogar o que estiver dentro das aspas no terminal

	else
		echo"Java não instalado";
		echo "Gostaria de Instalar o Java? [s/n]";

	read testeVar #o read serve para iniciar uma pergunta, como se fosse o scanner do JAVA, e o valor de retorno é armazenada da VAR (get)
	if [ \"$testeVar\" == \"s\"]; #Aqui ele vai ver a resposta de cima, então pra apresentação, COLOQUEI (s) E NÃO (SIM) POR FAVOR!!

	then
		sudo apt install openjdk-17-jre -y #ele vai jogar caso ele caia no IF de cima

	else # NÃO EXCLUA, QUERO FAZER UMA GRAÇA
		echo "beleza então fiote, se ferrou"
		sudo snap install terminal-parrot
		terminal-parrot
	fi
fi


