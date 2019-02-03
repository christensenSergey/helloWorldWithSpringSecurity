# helloWorldWithSpringSecurity //Java web app

Environment: Java 8, H2 database.

App is working on port 8443 via HTTPS to make it more securely.
It got self signed cerficate which is not trusted but show HTTPS works fine.
Predefined user with login: "admin" and password: "admin" inserted in DB.

There are few regexp in app to check password strength (
regexp correctness is not the subject of this task):
- for all patterns: at least 8 characters, no whitespace allowed in the entire string;
- weakPattern = digit must occur at least once, lower case letter must occur at least once;
- normalPattern = all from weakPattern and an upper case letter must occur at least once;
- strongPattern = all from normalPattern and a special character @#$%^&+= must occur at least once.

Dont forget to change "application.properties" as you need.

To start app execute in command line from project folder:
- mvn package //to compile project;
- mvn spring-boot:run //to run project on server.

Task: 

We want to protect our business critical “Hello World!” web page from non-registered users. Only registered users are granted access to the page.

Functional requirements: 

- Anonymous users may register by creating a user account from the login page 
- A user account consists of a login name and a password 
- The password of an account is required to be strong 
- Users that login with a valid login name and password are authenticated. 
Only authenticated users may view the “Hello World!” dummy page o Visual strength indicator while setting up the password using Ajax 

The algorithm is so complex and business critical that it must be performed server side. 

Technical requirements: 

- Use Spring Framework as a container 
- Use Spring Security to handle authentication 
- Store the account settings in a SQL database using Hibernate (you can use any DB either in memory - H2, HSQL or MySql) 
- Store passwords securely 
- Multiple browser support 
- Use valid XHTML and CSS 
- Must provide unit tests using JUnit 
- Use the principles of test driven development
