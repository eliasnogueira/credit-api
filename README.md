# Combined Credit API
This project was created to start the initial steps with test automation for a REST API using RestAssured.
It's a combination of the following projects to facilitate the test automation:
* [credit-restriction-api](https://github.com/eliasnogueira/credit-restriction-api)
* [credit-simulation-api](https://github.com/eliasnogueira/credit-simulation-api)

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
After the application has started open the link: http://localhost:8088/swagger-ui/index.html

## How to access the production endpoint
It's not really a production environment, but the API is also deployed on Heroku.
I added it there to enable you to play with different environment where localhost would be your development environment, 
and the heroku one would be your production environment.

You can use the following base URI to use as a production environment: 
[https://eliasnogueira-credit-api.herokuapp.com/](https://eliasnogueira-credit-api.herokuapp.com/)

# Do you want to help?
Please read the [Contribution guide](CONTRIBUTING.md)
