# Setting up, running, testing

- [Setting up, running, testing](#setting-up-running-testing)
  - [Technologies](#technologies)
    - [Backend](#backend)
    - [Database](#database)
    - [Frontend](#frontend)
  - [Requirements](#requirements)
  - [Setting up](#setting-up)
  - [Running](#running)
    - [Testing](#testing)

## Technologies

### Backend
 - Spring Boot
 - Spring Data Jdbc
 - Testcontainers
 - Flyway
 - Lombok

### Database

- Postgres
- PgAdmin

### Frontend

- Angular
- Tailwind CSS


## Requirements

Java 17,
Node, can be installed using nvm,

Ide preferably Intellij Idea Ultimate, however it is possible to use vscode or just terminal instead.

Docker is a must due to postgres database and integration tests.


## Setting up

Frontend reqires installing dependencies using

```
npm run install
```

## Running

Multiple ways to run backend and frontend application.
The easiest way is using docker-compose with docker-compose-full.yml file which contains backend, frontend and database. However tests won't be running there due to Testcontainers requiring docker.

Here is the command:
```
docker-compose up -f docker-compose-full.yml
```

Second way to run is through ide, but first database has to be 
setup first just using ```docker-compose up``` command. Intellij Idea is straight forward. Either using gradle tasks or straight going to main [application file](./backend/src/main/java/ee/erik/backend/Application.java). Frontend can also be launched using Intellij run button (Only Ultimate version supported for this).

Third way to run is using terminal.
In backend folder just run command:
```
./gradlew.bat :bootRun
``` 
on windows or on linux
```
./gradlew :bootRun
```

Frontend is launched in frontend folder using 
```
npm run start
```


### Testing

To test backend, docker instance must be running otherwise
integration tests will fail. The default database isn't needed here
as Testcontainers library takes care of that and creates test database for integration tests.

Using Intellij, run gradle test task
or from terminal 
```
./gradlew.bat test -i
``` 
on windows or on linux
```
./gradlew test -i
```
