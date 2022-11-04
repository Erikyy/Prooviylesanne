# Documentation

- [Documentation](#documentation)
  - [Database](#database)
  - [Backend](#backend)
    - [Folder structure and roles](#folder-structure-and-roles)
      - [application](#application)
      - [domain](#domain)
      - [infrastructure](#infrastructure)
      - [impl](#impl)
  - [Frontend](#frontend)
    - [folders](#folders)
    - [Models](#models)
    - [Pages](#pages)


## Database

## Backend

### Folder structure and roles

The main folders in backend are

- application
- domain
- infrastructure
- impl

#### application

Application layer contains DTO's (Data Transfer Objects), EventManager and PaymentMethodManager.
Managers are interfaced in Impl/Presentation folder.


#### domain

Domain folder contains domain entities which are just pure java classes, only use lombok annotations to remove some of the boilerplate code, EventService where most of the business logic is implemented then the exceptions that are thrown at runtime, using global exception handler in infrastructure layer and repository interfaces, those are implemented in infrastructure layer.

#### infrastructure

Infrastructure folder contains all the database repositories, domain repository implementations,
database entity mappers with convertes to domain entites and back and global exception handler for throwing http errors.

#### impl

Implementation folder or presentation folder
contains rest api implementation using Event manager and PaymentaMethod Manager. Rest api also contains swagger doc where are all rest api routes and methods documented. 

## Frontend

### folders

Frontend contains all the modules inside app folder

- events module
- shared module

and model just contains all types for events, participants, citizen, business which participant is used in and payment methods.

### Models

Events
Participants
Citizen - Participant as citizen
Business - Participant as business

### Pages

App routing only defines page not found page,
other routes are defined in events folder.

- Add event page - in add-event folder
- View event details + particpants view + adding - in events folder
- Participant detail view with update feature - participant folder
- Main page - index folder inside events folder