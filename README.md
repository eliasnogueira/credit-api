# Combined Credit API
This project was created to start the initial steps with test automation for a REST API using RestAssured.
It's a combination of the following projects to facilitate the test automation:
* [credit-restriction-api](https://github.com/eliasnogueira/credit-restriction-api)
* [credit-simulator-api](https://github.com/eliasnogueira/credit-simulator-api)

# Required software
* Java JDK 11+
* Maven installed and in your classpath

# About the API

## How to start the API
First, clone or download this project. After:
1. Navigate to the project folder using the Terminal / Command prompt
2. Execute the following: `mvn spring-boot:run`
3. Wait until you see something like this: _Application has started! Happy tests!_

## How to change the port
The default port is `8088`.
If you want to change the port to another one, execute the following command, replacing the text `<NEW_PORT>`.

Changing the port running the application using the jar file
```shell
java -jar credit-api-VERSION.jar --server-port=8089
```

Changing the port running the application using mvn
```shell
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=<NEW_PORT>
```

## How to access the documentation (Swagger)
After the application has started open the link: http://localhost:8088/swagger-ui.html

# Do you want to help?
Please read the [Contribution guide](CONTRIBUTING.md)
