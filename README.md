This Spring MVC application uses and end to end registration feature that I developed. The purpose of the application is to provide a way 
for managers at a company to have a secure platform for communicating with their employees.

Users are able to register, log in and log out. When a new user is registered, their password is encrypted with the help of Spring Security. 
The data for this application is saved in MySQL tables and I have established a many to many relationship between the JPA entities to let 
administrators keep track of each user's roles in the company.

Follow the steps below to launch the application:

1. Set up MySQL on your machine.
2. Edit the application.properties file to reflect the credentials for your MySQL server.
3. Run the project from the SecurityApplication class in your IDE.
4. By default the application's address is localhost:8080
