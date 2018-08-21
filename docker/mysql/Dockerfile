FROM registry.saas.hand-china.com/tools/mysql:5.6

ADD im.sh /im.sh

COPY sakila-schema.sql /mysql/sakila-schema.sql
COPY sakila-data.sql /mysql/sakila-data.sql

RUN chmod 777 im.sh

RUN cat /mysql/sakila-schema.sql > /docker-entrypoint-initdb.d/dump.sql
RUN cat /mysql/sakila-data.sql >> /docker-entrypoint-initdb.d/dump.sql

