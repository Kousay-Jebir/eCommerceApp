## Java E-Commerce Console App

### Overview

Welcome to the Java E-Commerce Console Application! This project is a hands-on exploration of e-commerce functionalities within a console-based environment. Developed for a college exam, this application showcases key concepts of Java programming and object-oriented design while simulating the essential aspects of an online shopping platform.

### Main Features

 1. **User Authentication**   
  
 2. **Product Management**  
 3. **Shopping Cart**  
 4. **Order Processing**  
 5. **Inventory Management**  
 6. **Dynamic Product Search and Filtering** 
 7. **Payment Processing**
  
### Optional Features Chosen
 1. **User Reviews and Ratings**  
- Users can add and view feedback on purchased products.  
 2. **Gifting Option**
 - Users are able to send gifts to eachother

### Technologies Used
-   Java
-   Object-Oriented Programming (OOP) principles

## Details aboout each feature
1. **Authentification**
Here is a simple UML class diagram that illustrates the design of my authentification system simulation.
![enter image description here](https://i.imgur.com/lgWlSkJ.png)
Users is a class that simulates a database , storing in a vecotr all kind of users , the attribute userAccessLevel	represents whether the user in quesion is an admin or a regular customer.
when the application shows the menu for the authentification section, the result of creating or logging in to a new account will results in an integer that represents the index of that user in the usersList array. That index is so important in my design because it is used to identify which user is currently logged in and using the application
together with the userAccessLevel these two variables will determine for  a given user instance whether it  has access to certain features and will apply  the changes to the appropriate instance of User in the users array
**In short words, Successfully logging in gives you the index of the account you logged into , and creating a new account automatically logs you in the index is used as an indicator to which user is currently using the application**
