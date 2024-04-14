# Fast Typing App - Backend
This app helps user to improve his typing skills. User selects length of text and try rewrite it without making mistakes
and as fast as possible. After rewriting the text, user can check statistics.

## Introduction
This project only contains api of the application, frontend is in a separate project on GitHub, link below:

`https://github.com/PiotrPiwowarski/fast-typing-fontend`

## Application Interface

![006878C1-0625-462C-AC57-858A336ADE21.png](..%2F..%2F..%2FPictures%2FPhotos%20Library.photoslibrary%2Foriginals%2F0%2F006878C1-0625-462C-AC57-858A336ADE21.png)

![6E8187F0-5657-4F83-9803-EB293102723A.png](..%2F..%2F..%2FPictures%2FPhotos%20Library.photoslibrary%2Foriginals%2F6%2F6E8187F0-5657-4F83-9803-EB293102723A.png)

![A76A5E30-AB37-4642-A06A-6B7FD9CAAE59.png](..%2F..%2F..%2FPictures%2FPhotos%20Library.photoslibrary%2Foriginals%2FA%2FA76A5E30-AB37-4642-A06A-6B7FD9CAAE59.png)

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

### Statistics
* This functionality is available using HTTP method `POST` under the endpoint: `/api/text`
* It requires request body containing following properties:  `userText`, `patternText` and `time`
* It returns object containing following properties: `correctWords`, `incorrectWords`, `accuracy`, `time`, `wordsPerMinute`