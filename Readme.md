
# Mortgage Plan

### *Web Application*
<br>


>#### 1. Installation & run instruction


###### To run application via Docker:

In terminal type:

    docker pull jonathanmterry/terrymortgage

    docker run -it --rm -p 8080:8080  jonathanmterry/terrymortgage 

Now application should be running at: http://localhost:8080


###### To run application via an IDE:
- Open the MortgagePlanWeb folder as project and hit run.
- After successful build the program should be listening on port: 8080
- Open any browser and enter: http://localhost:8080 
- And the Mortgage Plan webb application should appear.
- To open H2 console go to: http://localhost:8080/h2-console

<br>

>#### 3. Application functionality


The Mortgage plan web application's basic functionality is to  take in a mortgage plan and calculate
the monthly payment. Based on total loan amount, loan time in years and an interest rate  a monthly  payment can be returned.

On initial run the program will read prospects.txt located in project root folder and add information from that file to the h2 database.
When browser is loaded the customer information from that file will be shown.   

A new customer can be added via the new customer button. This will route the user to a new form where customer and loan information can 
be added. All fields of the form have to be filled and a new customer incompletely filled in would result in a redirect back to same form 

To remove a customer pres the yellow X button.

>#### 3. About application


This is my first Spring Boot application and Java Web application. 
<br>I used this tutorial by [Wazoo Web Bytes](https://www.youtube.com/watch?v=Hvuij8SOW8Q)  to a great extent. and would recommend anyone to check it out

| Version info                                           | Dependencies                                                                                                                     |   
|--------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| OpenJDK version 19.0.3<br/>Spring Boot 3.0.1<br/>Maven | - Spring Web<br/> - Spring Boot DevTools<br/> - Spring Data JPA<br/> - H2 Database<br/>- Thymeleaf<br/>- Lombok<br/>- Validation |
