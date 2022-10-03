# Product-Search-Project
*Simple program to load data to local relational DB then support Solr search*

testsql 
--
Java program to load tsv data to local Mysql DB.

1. Set configuration of db
```
$ cd testsql/src
$ vim jdbc.properties

USER = <USERNAME>
PASS = <PASSWORD>
DB_URL = jdbc:mysql://localhost:3306/<DATABASE_NAME>?allowLoadLocalInfile=true 
JDBC_DRIVER = com.mysql.cj.jdbc.Driver
```
2. Run DataLoad.java
```
javac DataLoad.java
java DataLoad
```

solr
--
Apache solr platform.

1. Run Locally 
```
# run locally and can be accessed http://localhost:8983/solr/#/new_core/core-overview 
solr/bin/solr start 
# stop running instance
solr/bin/solr stop
```
2. Search API to extractdata 

manipulate in http://localhost:8983/solr/#/new_core/query
or send request as the format: 
```
http://localhost:8983/solr/new_core/select?<query> 
```
e.g.http://localhost:8983/solr/new_core/select?fq=AverageRating%3A%5B4.8%20TO%205.0%5D&indent=true&q.op=OR&q=ProdDescription%3A*Jacket&rows=10000&sort=AverageRating%20desc  

