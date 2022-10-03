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

1. Execute 
```

```
2. 
```

```
