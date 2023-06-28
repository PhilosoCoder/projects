# Airplanes Spring Boot Project

This application controls airplanes and routes for administration purposes.

It is a classic 3-layered application with a MariaDB database, Java Spring backend, and REST services.

There is a two-way, one-to-many connection between the airplane and route entities.

Technologies used:

-SQL database handler layer with Spring Data JPA (Repository)
-Liquibase
-Business logic layer with @Service classes
-Controller layer with RESTful API
-Exception handling and validation
-Type conversions with MapStruct


Docker command to run the database:

```shell
docker run -d -p 3306:3306 -e MYSQL_DATABASE=airplanes -e MYSQL_USER=airplanes -e MYSQL_PASSWORD=airplanes -e MYSQL_ALLOW_EMPTY_PASSWORD=yes --name airplanes-mariadb mariadb
```