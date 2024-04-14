# Fast Typing App - Backend
This app helps user to improve his typing skills. User selects length of text and try rewrite it without making mistakes
and as fast as possible. After rewriting the text, user can check statistics.

## Introduction
This project only contains api of the application, frontend is in a separate project on GitHub, link below:

`https://github.com/PiotrPiwowarski/fast-typing-fontend`

## Technologies
This Rest API is created using these technologies:
* Java, version: 22
* Spring Boot, version: 3.2.4
* Spring data, version: suitable for version Spring Boot
* H2 database, version: 2.2.224
* Lombok, version: 1.18.32
* JUnit, version: 5.10.2
* Mockito, version: 5.11.0

## Database
* H2

I decided to use H2 database that only runs at runtime. This solution is sufficient for this project.

There are two .sql files in `src/main/resources`:
* `schema.sql` - it is used to build the database in runtime
* `data.sql` - it fills the database with data

## Functionalities
### Get Random Text
* This functionality is available using HTTP method `GET` under the endpoint: `/api/text`
* You can specify length of text using request param `length` 
* It returns object containing following property: `patternText` 

### Get Statistics
* This functionality is available using HTTP method `POST` under the endpoint: `/api/text`
* It requires request body containing following properties:  `userText`, `patternText` and `time`
* It returns object containing following properties: `correctWords`, `incorrectWords`, `accuracy`, `time`, `wordsPerMinute`