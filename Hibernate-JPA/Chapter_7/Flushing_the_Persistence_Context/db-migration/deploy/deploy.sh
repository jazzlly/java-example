#!/usr/bin/env bash
# mysql's mysqld sector in /etc/my.cnf add if not exists flowing tow lines
#lower_case_table_names=1
#character-set-server=utf8

dbName=security_rule
master=mysql.csr.pekall.com

mysql -h ${master} -uroot -ppekall1234 -e "create database if not exists ${dbName} character set utf8;"

cd ../
mvn clean compile flyway:migrate -DdbName=${dbName} -Dmaster=${master}
cd deploy

