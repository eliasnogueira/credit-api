# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.10.1] - 06-11-2023

## Changed
- Update `pom.xml` to use Java 21
- Updated the following libraries
  - `spring-boot-starter-parent -> 3.1.5`
  - `maven-surefire-plugin.version -> 3.2.1`
  - `modelmapper.version -> 3.2.0`
  - `lombok.version -> 1.18.30`
  - `jakarta-xml-bind-api.version -> 4.0.1`
  - `h2.version -> 2.2.224`
  - `guava.version -> 32.1.3-jre`
  - `snakeyaml.version -> 2.2`
- Update Maven Wrapper
- Update GitHub action to use Java 21

## [1.9.1] - 27-08-2023

### Changed
- Updated the following libraries
  - `spring-boot-starter-parent -> 3.1.2`
  - `springdoc-openapi-starter-webmvc-ui -> 2.2.0`
  - `h2 -> 2.2.220`
  - `commons-codec -> 1.16.0`
  - `jackson-databind -> 2.15.2`
  - `guava -> 32.1.2-jre`

## [1.9.0] - 01-06-2023

### Added
- Added the Maven Wrapper

### Changed
- Updated the following libraries
  - `spring-boot-starter-parent -> 3.1.0`
  - `lombok.version -> 1.18.28`
  - `hibernate-jpamodelgen.version -> 6.2.2.Final`
  - `jackson-databind.version -> 2.15.0`

## [1.8.6] - 04-05-2023

### Changed
- Updated the following libraries
  - `spring-boot-starter-parent -> 3.0.6`
  - `springdoc-openapi-starter-webmvc-ui.version -> 2.1.0`
  - `lombok.version -> 1.18.26`
  - `hibernate-jpamodelgen.version -> 6.2.3.Final`
  - `maven-compiler-plugin.version -> 3.11.0`
  - `maven-surefire-plugin.version -> 3.1.0`
  - `jib.version -> 3.3.2`
  - `guava.version -> 32.0.0-jre`

## [1.8.5] - 23-01-2023

### Changed
- Fix the `Message` model in the OpenAPI spec

## [1.8.4] - 21-01-2023

### Added
- Custom banner

### Changed
- Internal request during `POST /simulations` to check for a restriction
- Bump `spring-boot-starter-parent` to `3.0.2`

## [1.8.3] - 15-01-2023

### Changed
- Fix path in the `GET` request for the `SimulationsController`
- Changed the initial load data in `LoadDatabase`

## [1.8.2] - 08-01-2023

### Added
- `RestTemplateErrorHandler` to control de errors and HTTP status when check for the restrictions

### Changed
- `SimulationsController` checking for a restriction before sending the `POST` request

## [1.8.1] - 31-12-2022

### Added
- `jvm.config` to remove Maven verbosity
- `Dockerfile` to generate a container

### Changed
- Fixed `start-class` property value in `pom.xml`
- Bump `spring-boot-starter-parent -> 3.0.1`
- Added `jib-maven-plugin` to push the project as a docker image

## [1.8.0] - 20-12-2022

### Changed
- Removal of database `derby`, replaced by `h2`
- Removal of `springdoc-openapi-ui`, replaced by `springdoc-openapi-starter-webmvc-ui`
- Added `spring.datasource` for `h2` at `application.properties`
- Changed the default OpenAPI URL in the `application.properties`
- Adoption of new packages for the OpenAPI in the `OpenApiConfig`

## [1.7.0] - 19-12-2022

### Changed
- Adoption of SpringBoot 3
- Updated the following libraries
  - `springdoc-openapi-ui -> 1.6.14`
  - `modelmapper -> 3.1.1`
  - `hibernate-jpamodelgen -> 6.1.6.Final`

## [1.6.1] - 06-12-2022

### Changed
- Removal of the test in favor of the branch `manage-data` for educational purposes

## [1.6.0] - 05-12-2022

###
- General improvements

## [1.5.0] - 24-07-2022

### Added
- Added the openapi spec as a yaml file at /src/main/resources/static
- Added `springdoc-openapi-ui` dependency and related changes

### Changed
- Removed the spring-fox annotation from several classes
- Update `spring-boot-starter-parent`, `junit`, `model-mapper`, `hibernate-jpamodelgen` and `lombok` library version
- Update actions in the GitHub workflow

### Removed
- Removed the spring-fox dependency and related code

## [1.4.8] - 19-04-2022

### Changed
- Updated the following libraries
  - `modelmapper-3.1.0`
  - `lombok-1.18.24`
  - `hibernate-jpamodelgen-6.0.0.Final`
- Added `jakarta.xml.bind-api` library to prevent `java.lang.NoClassDefFoundError: jakarta/xml/bind/JAXBException: jakarta.xml.bind.JAXBException` 
- OpenAPIs docs link updated in the `README.md` file

## [1.4.7] - 23-05-2021

### Changed
- Dummy PR to solve GitHub Packages problem

## [1.4.7] - 16-02-2021

### Changed
- Updated the following dependencies
  - `modelmapper-2.4.3`
  - `lombok-1.18.20`
  - `hibernate-jpamodelgen-6.0.0.Alpha8`
  - `spring-boot-starter-parent-2.5.0`
- Added missing `insurance` field in the simulation update

## [1.4.6] - 14-02-2021

### Changed
- General refactor in some classes
- Data changed in the `LoadDatabase` class
- `spring-boot-starter-parent` version update

## [1.4.5] - 06-02-2021

### Changed
- Renamed the Simulation advice
- Added generic exception for Simulation not found by name

### Removed
- Custom exception for Simulation not found by name

## [1.4.4] - 06-02-2021

### Changed
- Bump version due to the GitHub Actions conflict with the previous version

## [1.4.3] - 06-02-2021

### Changed
- Renamed all the classes replacing Simulator by Simulation
- Changed the Api response example for the GET and PUT endpoints on SimulationController  

## [1.4.2] - 05-02-2021

### Added
- Added `system.properties` file to enable the Heroku deploy

## [1.4.1] - 05-02-2021

### Added
- Added `spring.profiles.active=default`

### Changed
- Updated lombok dependency to `1.18.18`
- Updated `README` with the two different ways to change the port

## [1.4.0] - 31-01-2021

### Changed
- Changed the log levels to remove the console logger verbosity 

## [1.3.0] - 31-01-2021

### Added
- GitHub workflow
- Added configuration for swagger on `application.properties`
- Added `springfox-swagger2` library
- Added `ApiParam` description for the `GET` mappings on `RestrictionController`

### Changed
- Updated the following library versions
    - spring-boot-starter-parent-2.4.1
    - springfox-3.0.0
    - maven.compiler.plugin-3.8.1
    - maven-failsafe.version>3.0.0-M5
  
### Removed
- Removed `SneakyThrows` on `SimulatorController`
- Removed `@Swagger2` annotation on `SwaggerConfig`
- Renamed the main class to `Run`