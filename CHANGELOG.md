# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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