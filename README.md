# Full-Stack Developer Sample Project


## Introduction
This is a small web application allowing you to register for the next Java Meetup. If the application is started, the pages are available under [http://localhost:8080/registration.html](http://localhost:8080/registration.html). After submitting your information, an ajax request will be sent to [http://localhost:8080/attendees](http://localhost:8080/attendees) REST endpoint. 

Data transfer objects(DTO) are used to transfer information between client and server, The service layer contains business logic and the repository layer is in charge of working with the database, which in this case is an in-memory relational database called H2. I used Spring Core for dependency injection, Spring Security for password hashing and Spring Data JPA to access the database. 

## Build and Run
This project uses Maven as its build tool. To build this project, run this maven command:

```
mvn clean package
```

The Maven build creates an executable jar. You can start the application with:

```
java -jar k15t-full-stack-dev-tasks-0.1.0.jar
```

## Validation
Validations are done both in frontend and backend, and some of them are as follows:

* Required fields are filled by user.
* Name only contain alphabetical characters and whitespaces.
* Name must be at least 3 characters in length.
* Phone number and email must match a certain pattern. 
* Phone number and email must be unique. 

## Security
Passwords must follow certain rules in order to considered strong enough. These rules are: 

* Password cannot contain whitespace characters.
* Password must contain at least one alphabetical characters.
* Password must contain at least one digit characters.
* Password must be no more than 16 characters in length.
* Password must be at least 8 characters in length.

I created a *@Password* annotation that validates the above rules using the *Passay* framework. Passay considered to be a password policy enforcement for Java and makes validating candidate passwords against a configurable rule set a lot easier.

I'm also hashing passwords before storing them into the database. Because Java security package does not support strong hashing algorithms like BCrypt or SCrypt, I added *Spring Security* dependency to the project and used its *BCryptPasswordEncoder*.

## Logging
I've added Logback configurations to the project. I created an Aspect using *Spring AOP* to log every input and output of the service layer's methods. I'm also masking sensitive information and ensure that fields like passwords never get logged. 

## Tests
There are a few unit tests which ensure that our repository layer always works as expected.

 