package interfaces;

import java.util.ArrayList;

import features.userAuth.User;

// THIS INTERFACE IS TO AVOID CODE DUPLICATION LIKE U TAUGHT US IN CLASS :D
// ENSURE THE IMPLEMENTATION OF COMMON METHODS ON THESE COLLECTABLES
// ANY COLLECTABLE SUCH AS USERS AND PRODUCTS WILL IMPLEMENT THIS INTERFACE
public interface Collectable {

    public void modifyCollectable();
}