# Üles sättimine, jooksutamine, testimine

- [Üles sättimine, jooksutamine, testimine](#üles-sättimine-jooksutamine-testimine)
  - [Tehnoloogiad](#tehnoloogiad)
    - [Backend](#backend)
    - [Database](#database)
    - [Frontend](#frontend)
  - [Nõuded](#nõuded)
  - [Üles sättimine](#üles-sättimine)
  - [Jooksutamine](#jooksutamine)
    - [Testimine](#testimine)


## Tehnoloogiad

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

## Nõuded

Java 17,
Node, võimalik installida läbi nvm,

Arenduskeskonnaks on soovituslik kasutada Intellij Idea Ultimate, aga on ka võimalik kasutada vscode või lihtsalt käsurea liidest.

Docker on vajalik Postgresa andmebaasi jaoks ja integratsiooni testide jaoks.

## Üles sättimine

Frontendil on vaja installida pakid kasutades

```
npm run install
```

## Jooksutamine

On mitu viisi kuidas jooksutada nii backendi kui ka frontendi rakendust. Kõige lihtsam viis on kasutada docker-compose koos docker-compose-full.yml failiga mis sisaldab backendi, frontendi kui ka andmebaasi. Kuid teste sellega ei jooksutata kuna backend kasutab Testcontainers pakki mis nõuab dockerit.

Siin on see käsklus: 
```
docker-compose up -f docker-compose-full.yml
```

Teine viis on kasutada Integreeritud arenduskeskkonda, aga kõigepealt peab andmebaasi üles sättima kasutades ```docker-compose up``` käsklust. Intellij Idea's on võimalik jooksutada backendi läbi gradle taski või minna rakenduse [põhifaili](./backend/src/main/java/ee/erik/backend/Application.java). Frontendi on ka võimalik jooksutada läbi Intellij Idea keskonna kasutades jooksutamis nuppu (Ainult Ultimate versioon on toetatud selles).

Kolmandaks viisik on jooksutada läbi käsurea liidese.
Backend kaustas jooksuta lihtsalt 
```
./gradlew.bat :bootRun
```
Windowsi peal või kui on kasutusel linux
```
./gradlew :bootRun
```

Frontendi saab jooksutada frontendi kaustas kasutades

```
npm run start
```
käsklust.

### Testimine

Et testida backend rakendust, docker peab olema käivitatud muidu integratsiooni testid põruvad. Andmebaasi pole vaja eraldi jooksutada kuna Testcontainers teeb selle töö ise ära tehes testi andmebaase.

Kasutades Intellij keskkonda, jooksuta gradle test task
või läbi käsurea liidese

```
./gradlew.bat test -i
``` 
windowsi peal või kui on kasutusel linux siis
```
./gradlew test -i
```