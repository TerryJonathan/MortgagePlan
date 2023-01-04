
# Mortgage Plan

### *Web Application*
***
<br>

>#### 1. Installation & run instruction
 
###### *Run application via AWS:*
http://ec2-54-236-109-101.compute-1.amazonaws.com:8080/ 
    
    Info to restart app:
    Hostname: ec2-54-236-109-101.compute-1.amazonaws.com 
    User name: ubuntu 
    Keys in MortgagePlanWeb folder: MortagePlanV2.ppk , MortagePlanV2.pem
###### *Run application via Docker:*
    docker pull jonathanmterry/mortgage_web
    docker run -it --rm -p 8080:8080  jonathanmterry/mortgage_web 

Now application should be running at: http://localhost:8080


###### *Run application via an IDE:*

    Open the MortgagePlanWeb folder as project and hit run.
    After successful build the program should be listening on port: 8080
Now application should be running at: http://localhost:8080 <br>
To open H2 console go to: http://localhost:8080/h2-console

***
<br><br>

>#### 2. Application functionality

The Mortgage plan web application's basic functionality is to  take in a mortgage plan and calculate
the monthly payment. Based on total loan amount, loan time in years and an interest rate  a monthly  payment can be returned.

On initial run the program will read prospects.txt located in project root folder and add information from that file to the h2 database.
When browser is loaded the customer information from that file will be shown.   

A new customer can be added via the new customer button. This will route the user to a new form where customer and loan information can 
be added. All fields of the form have to be filled and a new customer incompletely filled in would result in a redirect back to same form 

To remove a customer pres the yellow X button.
***
<br><br>
>#### 3. About application
I used this tutorial by [Wazoo Web Bytes](https://www.youtube.com/watch?v=Hvuij8SOW8Q)  for inspiration and would recommend anyone to check it out.

| Version info                                           | Dependencies                                                                                                                     |   
|--------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| OpenJDK version 19.0.3<br/>Spring Boot 3.0.1<br/>Maven | - Spring Web<br/> - Spring Boot DevTools<br/> - Spring Data JPA<br/> - H2 Database<br/>- Thymeleaf<br/>- Lombok<br/>- Validation |
