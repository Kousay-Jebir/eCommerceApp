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
1. **UtilityClasses**

During the development process , a problem of huge code duplication occured , it seems that there is a lot of common logic and methods between Users and Products in terms of they both are classes that store a vector of that type and have methods such as addUser , addProudct . to solve this issue i implemented an interface called Collectable 
the User and Product classes both implement the interface 
the utilityClass Collection<T> solves the code duplication issue so that it supports any type that we might need a vector for (here Users and products) where <T> represents a generic Type to avoid type restriction issues if we were to use Collectable as a type instead :D 

Here is a diagram : 
![](https://i.imgur.com/mdb1ygB.jpg)

1. **Authentification**
Here is a simple UML class diagram that illustrates the design of my authentification system simulation.
![enter image description here](https://i.imgur.com/lgWlSkJ.png)
Collection<User> is a class that simulates a database , storing in a vecotr all kind of users , the attribute userAccessLevel	represents whether the user in quesion is an admin or a regular customer.
when the application shows the menu for the authentification section, the result of creating or logging in to a new account will results in an integer that represents the index of that user in the usersList array. That index is so important in my design because it is used to identify which user is currently logged in and using the application
together with the userAccessLevel these two variables will determine for  a given user instance whether it  has access to certain features and will apply  the changes to the appropriate instance of User in the users array
**In short words, Successfully logging in gives you the index of the account you logged into , and creating a new account automatically logs you in the index is used as an indicator to which user is currently using the application**
2. **Product Management**
Similar to users , Collection<Product> is used to to store all kind of products 
Products are classified by a Category ( either a computer or a Camera )
![enter image description here](https://i.imgur.com/NENqgZ6.jpg)
3. **CRUD Operations on products and users**
each admin is supposed to work on only one Category of a product and perform the following operations : 
Modify a give product , delete it , add a new one 
Moderators are admins who can perform the same operation on the database of Users ( they work on the category "USER")
whereas customers will not be able to perform these actions 
The logic of this is implemented throught the Admin menu
4. **Interactive men**
The application as a whole is usuable via an interactive console interface menu
Types of menus you will see are : 
- Authentification menu 
- Admin is logged in menu includes welcome page, dashboard for moderation and stock management
- Customer is logged in menu includes welcome page, shopping interface , shopping cart interface and other .. 
These menus are implemented throught various methods in each classes 
