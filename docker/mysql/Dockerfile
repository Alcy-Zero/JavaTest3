FROM registry.saas.hand-china.com/tools/mysql:5.6

ENV AUTO_RUN_DIR /docker-entrypoint-initdb.d

ENV FILE1 sakila-schema.sql
ENV FILE2 sakila-data.sql

ENV INSTALL_DATA_SHELL install_data.sh

RUN mkdir -p /usr/local/work

COPY ./$FILE1 /usr/local/work/
COPY ./$FILE2 /usr/local/work/

COPY ./$INSTALL_DATA_SHELL $AUTO_RUN_DIR/

RUN chmod a+x $AUTO_RUN_DIR/$INSTALL_DATA_SHELL