## Gas & Water Usage Monitoring Application

##### java version: 1.8
##### spring version: 2.7.0
##### database: HSQLDB 2.7.2

## DB connection guide:
1. download zip from https://sourceforge.net/projects/hsqldb/files/
2. unzip downloaded file and go to the unzipped folder
3. create 'server.properties' file and add to it:

server.database.0 = file:hsqldb/your_database

server.dbname.0 = your_db_name

your_database is a folder name for create db

your_db_name is a name of new db

4. open shell, move to the folder with db from step 2 and run next command: (it will create new DB from config)

java -classpath lib/hsqldb.jar org.hsqldb.server.Server
5. then run created db with following command:

java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/<your_database> --dbname.0 <your_db_name>

## Before running application:
1. build project with Maven

mvn compile

mvn package
2. set environment variables:

SPRING_DATASOURCE_DBNAME: <your_db_name>

SPRING_DATASOURCE_USERNAME: sa (or your value, if set another)
SPRING_DATASOURCE_PASSWORD: (empty string or your value, if set)
3. apply liquibase db migrations. Open shell, move to project root:

cd liquibase/

./liquibase --url=jdbc:hsqldb:hsql://localhost/<your_db_name> --username=<SPRING_DATASOURCE_USERNAME> --password=<SPRING_DATASOURCE_PASSWORD> --changeLogFile=/db.changelog/db.changelog-master.yaml update

example:
./liquibase --url=jdbc:hsqldb:hsql://localhost/tz --username=sa --password="" --changeLogFile=/db.changelog/db.changelog-master.yaml update

4. run project


## App manual:
Two test users are initially added to the database:
1) email: user1@example.com | password: test1
2) email: user2@example.com | password: test2

First of all you need to log in using POST /v1/auth/login. Endpoint returns access token using which you could use secured endpoints like a:

GET /v1/measurements/latest - get latest measurements for user

GET /v1/measurements/history - get measurements history for user

POST /v1/measurements - save new measurements. Measurements must be greater than or equal to previous ones

## Response codes:
0 - OK

1 - unknown error (details in message)

101 - invalid incoming data

102 - save measurements error

103 - measurements not found

401 - unauthorised

For more information about application API use swagger-ui (when app is running):

http://localhost:8080/swagger-ui.html
