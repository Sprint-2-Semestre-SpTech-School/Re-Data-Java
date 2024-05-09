#!/bin/bash

echo "Bem vindo ao instalador Java!!"
echo "Desenvolvido pela Re;Data para e SOMENTE PARA uso de clientes contratantes dos serviços de monitoramento"

java -version 

if [ $? == 0 ]; #Aqui ele vai perguntar o valor de retorno do comando de cima (que vai pergunta a vsersão do Java)
	then
		echo "JAVA INSTALADO!" #toda vez que vir um (echo) é porque ele vai jogar o que estiver dentro das aspas no terminal

	else
		echo"Java não instalado";
		echo "Gostaria de Instalar o Java? [sim/nao]";

	read testeVar #o read serve para iniciar uma pergunta, como se fosse o scanner do JAVA, e o valor de retorno é armazenada da VAR (get)

	if [ \"$testeVar\" == \"s\" ]; #Aqui ele vai ver a resposta de cima, então pra apresentação, COLOQUEI (s) E NÃO (SIM) POR FAVOR!!

	then
		sudo apt install openjdk-17-jre -y #ele vai jogar caso ele caia no IF de cima

	else 
		exit

	fi
fi


mysql --version

if [ $? == 0 ];
        then 
                echo "O mysql instalado na máquina!"
        else 
                echo "Mysql não foi encontrado, deseja instala-lo"

        read mysql

        if [ \"$mysql\" == \"s\" ];
	then
        	sudo apt install -y mysql-server
        	sudo system start mysql.service
        	sudo mysql
        	# ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'urubu100';
        	mysql -u root -p < "Script BD Sprint.sql"
	fi

fi
