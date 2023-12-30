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

## Details aboout each feature and design decisions 
1. **UtilityClasses (design decision)** 

During the development process , a problem of huge code duplication occured , it seems that there is a lot of common logic and methods between Users and Products in terms of they both are classes that store a vector of that type and have methods such as addUser , addProudct . to solve this issue i implemented an interface called Collectable 
the User and Product classes both implement the interface 
the utilityClass Collection<T> solves the code duplication issue so that it supports any type that we might need a vector for (here Users and products) where <T> represents a generic Type to avoid type restriction issues if we were to use Collectable as a type instead :D 

Here is a diagram : 
![](https://i.imgur.com/mdb1ygB.jpg)

1. **Authentification (feature)**
Here is a simple UML class diagram that illustrates the design of my authentification system simulation.
![enter image description here](https://i.imgur.com/lgWlSkJ.png)
Collection<User> is a class that simulates a database , storing in a vecotr all kind of users , the attribute userAccessLevel	represents whether the user in quesion is an admin or a regular customer.
when the application shows the menu for the authentification section, the result of creating or logging in to a new account will results in an integer that represents the index of that user in the usersList array. That index is so important in my design because it is used to identify which user is currently logged in and using the application
together with the userAccessLevel these two variables will determine for  a given user instance whether it  has access to certain features and will apply  the changes to the appropriate instance of User in the users array
**In short words, Successfully logging in gives you the index of the account you logged into , and creating a new account automatically logs you in the index is used as an indicator to which user is currently using the application**
2. **Product Management (feature)**
Similar to users , Collection<Product> is used to to store all kind of products 
Products are classified by a Category ( either a computer or a Camera )
![enter image description here](https://i.imgur.com/NENqgZ6.jpg)
3. **CRUD Operations on products and users (feature)**
each admin is supposed to work on only one Category of a product and perform the following operations : 
Modify a give product , delete it , add a new one 
Moderators are admins who can perform the same operation on the database of Users ( they work on the category "USER")
whereas customers will not be able to perform these actions 
The logic of this is implemented throught the Admin menu
4. **Interactive menues (feature/design decision)**
The application as a whole is usuable via an interactive console interface menu
Types of menus you will see are : 
- Authentification menu 
- Admin is logged in menu includes welcome page, dashboard for moderation and stock management
- Customer is logged in menu includes welcome page, shopping interface , shopping cart interface and other ..
- These menus are implemented throught various methods in each classes 
![enter image description here](https://i.imgur.com/Hd79JOh.jpg)
5. **Shopping Cart (feature)**
Under the realization of the following fact:
a shopping cart can only make sense when there is a user otherwise it doesn't exist
it it safe to say that there is a composition relationship between a Customer and the shopping cart
i've decided to implement a class called CartProduct which extracts the information needed from an abstract Product
because we don't need to store all the information about a certain product in a user's shopping cart
for exemple , if the shopping cart has an  Array of products that would be innacurate because the shopping cart doesn't need to know about for exemple the product's quantity in stock or other information 
so this class is used to sort of adapt a Product to be part of the shopping's cart list (represented by a vector)
the user is able to interact with his cart throught a menu and perform actions ( Add/remove quantity to a certain product already in his cart , remove a product from his list etc..)
all of this is ensured via the Cart class 
here is a diagram that explains the OOP implementation of the shopping cart
![enter image description here](https://i.imgur.com/qzubqsH.jpg)
6. **Dynamic product search and filtering (feature)**
The user is able ( via the shopping page interface throught the menu) to perform two search actions :
- List all products in stock : 
this option is equivalent to searching without provided user restrictions
- search a product via its label :
the class SearchProduct provides a method that searches an item from a given user input and returns it for display 
- auto searching vis filtering :
the user is able to choose to apply a certain filtering option. A method provided by the FilteredProducts is responsible to generate a new collection of items based on the user's filter choice. The new collection is then provided back to be displayed in the menu 
![enter image description here](https://i.imgur.com/8InQF7z.jpg)
7. **Payment checkout + Inventory updates (feature)**
given the user's cart , when a user attemps to checkout his purchase the appropriate methods provided by PaymentCheckout class are responsible for checking if the quantities in stock for each item the user is trying to purchase is available or not .
I provided implementation to handle the scneario where it is not , therefore the payment is refused  , otherwise it is accepted the cart is then cleared and the user's order is saved in a history 
Please checkout the PaymentCheckout class i implemented for the detailed logic .
8. **Transaction history (feature)**
similary to the cart design , there is a composition relationship between the Customer class and Order class .
the customer , when instanciated is given a Collection of Orders 
of course i used my utility class Collection
The Order class represents an order with its id , the username of whoever made the order and of course the content of the order ,
the user is able to access his history throughout the Customer menu 
![enter image description here](https://i.imgur.com/dxeBPDm.jpg)
