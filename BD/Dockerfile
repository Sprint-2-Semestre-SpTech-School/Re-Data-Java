FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=urubu100

COPY ./script/ScriptMYSQL.sql ./docker-entrypoint-initdb.d/

EXPOSE 3306
