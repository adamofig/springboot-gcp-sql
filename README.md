# Spring Cloud GCP DB SQL Example

This project is an example to deploy and connect **Spring-boot** application with **Google Cloud Platform**  - **Sql Cloud**.
requirements:  
* Database second generation and project with Java 8 on Appengine.
* To have installed gcloud sdk with and Java components.

## Understanding what i did
We use demonstrates how to use Spring JDBC and spring data but we use the original repository examples from GoogleCloudPlatform

1. To add compatibility with app engine standard was followed the next instructions, https://github.com/GoogleCloudPlatform/getting-started-java/tree/master/appengine-standard-java8/springboot-appengine-standard
Summary: 
* Was changed Jar to War
* Was created a new class ServletInitializer  to initialize servlets
* Was removed tomcat to left jetty as default
* Was added appengine plugin and excluded jul-to-slf4j
* Was added appengine-web.xml and logging.properties

2. To do a connection using JDBC was reused the code from the next repository  https://github.com/spring-cloud/spring-cloud-gcp/tree/master/spring-cloud-gcp-samples/spring-cloud-gcp-sql-sample
Summary: 
* Created a database with user and add values at run time using schema.sql and data.sql files. 
* *when clone and run the project go to http://localhost:8080/getTuples to test.

3. To do connection with Spring data was using the next repository,
https://github.com/spring-cloud/spring-cloud-gcp/tree/master/spring-cloud-gcp-samples/spring-cloud-gcp-data-jpa-sample
Summary:
* When apps run, create a table in the database which adds a new record
 * To test http://localhost:8080/getHouses you will see your record.


## Setting preview for the project

1. Grant permissions for connecting database on the next link
https://console.cloud.google.com/iam-admin/iam
select your project, then on IAM section find the default account should be similar to your-project-id@appspot.gserviceaccount.com, 
click on edit icon and add Client SQL permissions on CloudSql->ClientSql 
now this account should have permissions for to operations on the database

2. Activate Google Cloud Sql Api https://console.cloud.google.com/apis/library
note: search exactly "Google Cloud Sql Api" 

3. Download your credentials on the next link.
https://console.cloud.google.com/apis/credentials/serviceaccountkey 
you have to selection App Engine service account, create credencials and download the JSON key, then copy and substitute this json on the project file src/main/resources/keys-gcp.json

4. Add properties to connect Google App Engine
go to file application.properties and fill the next empty properties as this example.

> * spring.cloud.gcp.sql.database-name= YOUR_DATABASE_NAME
 * spring.cloud.gcp.sql.instance-connection-name= YOUR:INTANCE:NAME 
 * spring.datasource.username= YOUR_DATABASE_USER
* spring.datasource.password= YOUR_DATABASE_PASSWORD
* spring.cloud.gcp.project-id= YOUR_PROJECT_ID

## Deploy To Google AppEngine

Make sure you are logged and have permissions of your project, use the next command
gcloud auth login 

Opcional : En el archivo pom.xml en la línea 76 en configuration project pegar el id del proyecto al cual desplegará

## Use as archetype

borrar directorios com/example/ → jdbc y jpa borrar los archivos de resources/ sql data.sql y schema.sql

opcional →refactorizar el nombre del paquete com.example con esto se tiene un proyecto limpio
