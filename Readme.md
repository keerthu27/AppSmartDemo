Customer Products Service API

This is REST Service Application. This service caters following operations
1. Create new Customer
2. Update Existing Customer
3. Delete Existing Customer
4. Returns paginated List of Existing Customer
5. Returen customer based on customer Id
6. Create new prodcut for particular Customer
7. Update product
8. Delete product
9. Returns paginated list of product for particular customer

Technology and Framework adapted to build this solution:
1. Java 11
2. Spring Boot
3. Spring Validation
4. Lombok
5. PostgresSQL
6. Maven
7. Junit

Install and Run the service API
1. Install Java 11
2. install Postgres 14

Database Setup
1. Create a database schema named CustomerServicedb
2. To create the table run Create_Database.sql (Path : AppSmartDemo/sec/main/resources)

Update datbase url, username and password in application.properties file (path: AppSmartDemo/sec/main/resources)

url=jdbc:postgresql://localhost:5432/CustomerServicedb
username=
password= 


mvn clean install
java -jar Demo-0.0.1-SNAPSHOT.jar




