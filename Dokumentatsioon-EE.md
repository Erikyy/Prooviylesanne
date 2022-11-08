# Dokumentatsioon

- [Dokumentatsioon](#dokumentatsioon)
  - [Andmebaas](#andmebaas)
  - [Backend](#backend)
    - [Kausta struktuur ja rollid](#kausta-struktuur-ja-rollid)
      - [application](#application)
      - [domain](#domain)
      - [infrastructure](#infrastructure)
      - [impl](#impl)
  - [Frontend](#frontend)
    - [kaustad](#kaustad)
    - [Mudelid](#mudelid)
    - [Leheküljed](#leheküljed)
  - [Rest API dokumentatsioon](#rest-api-dokumentatsioon)

## Andmebaas

Andmebaasi diagramm on näidatud selles pildis:

![Database structure](./db-schema.png)

Intellij Idea keskonnas on ka võimalik näha andmebaasi diagrammi.
https://www.jetbrains.com/help/idea/creating-diagrams.html

## Backend

### Kausta struktuur ja rollid

Põhilised kaustad backendis on

- application
- domain
- infrastructure
- impl

#### application

Application tasemel on DTO'd (Data Transfer Objects),
EventManager ja PaymentMethodManager. Kõik manager klassid kasutatakse Impl/Presentatsiooni kaustas

#### domain

Domeeni kaustas sisaldab domeeni üksusi/objekte mis on puhtad java klassid mis kasutavad ainult Lombokit et eemaldada boilerplate koodi, EventService kus on implementeeritud enamus rakenduse äriloogiat siis on seal exception classid mida Globaalne exception handler kasutab infrastruktuuri tasemel, repository liidesed mida on implementeeritud ka infrastruktuuri tasemel.

#### infrastructure

Infrastructure kasutas sisaldab kõik andmebaasi repository liideseid, domeeni repository implementatsioone, andmebaasi entity/mudeli kaardistajad koos muunduritega mis teevad andmebaasi mudeli domeeni mudeliks ning vastupidi.

#### impl

Implementatsiooni/Presentatsiooni tasemel sisaldab rest api implementatsioon kasutades EventManager ja PaymentMethodManager klassi. Rest api sisaldab ka swagger dokumentatsiooni kus on ära dokumenteeritud kõik api teed ning meetodid.

## Frontend

### kaustad

Frontend sisaldab kõik moodulid app kaustas

- events module
- shared module

ja model kasutustas on defineeritud vajalikud tüübid events, participants, citizen, businenss mida participant kasutab ning payment methods.

### Mudelid

Events - Üritused
Participants - Osalejad
Citizen - Osaleja eraisikuna
Business - Osaleja ettevõttena

### Leheküljed

App routing defineerib ainult lehekülge ei leitud lehte,
teised lehed on defineeritud events kaustas.

- Ürituse lisamise lehekülg - add-event kaustas
- Ürituse uuendamise lehekülg - update-event kaustas
- Ürituse vaatamine + osalejate vaade + lisamine - event kaustas
- Osaleja vaade koos muutmisega - participant kaustas
- Avaleht - events kausta sees index kaustas

## Rest API dokumentatsioon

Rest api dokumentatsioon asub http://your_ip:yourport/swagger-ui/index.html#/ aga saab ka leida [backend rest implementatsiooni](./backend/src/main/java/ee/erik/backend/impl/rest/) kontrolleritest annoteeritud OpenApi'ga.
