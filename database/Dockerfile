FROM mysql:8

ENV MYSQL_ROOT_PASSWORD=password

COPY ./data.sql /docker-entrypoint-initdb.d/
