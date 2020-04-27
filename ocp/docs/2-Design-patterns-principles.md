# Design Patterns and Principles

## Designing an Interface
### Purpose of an Interface

## Introducing Functional Programming

### Defining a Functional Interface
### Implementing Functional Interfaces with Lambdas
### Applying the Predicate Interface

## Implementing Polymorphism
### Distinguishing between an Object and a Reference
### Casting Object References

## Understanding Design Principles
### Encapsulating Data
### Creating JavaBeans
### Applying the Is‐a Relationship
### Applying the Has‐a Relationship
### Composing Objects

## Working with Design Patterns

### Applying the Singleton Pattern

The _singleton pattern_ is a creational pattern focused on creating only one instance of an object in memory within an application, sharable by all classes and threads within the application

If all of the constructors are declared private in the singleton class, then it is impossible to create a subclass with a valid constructor; therefore, the singleton class is effectively `final`.

 the static initialization block allows additional steps to be taken to set up the singleton after it has been created.

#### Applying Lazy Instantiation to Singletons
 ```java
 // Lazy instantiation
public class VisitorTicketTracker {
    private static VisitorTicketTracker instance;
    private VisitorTicketTracker() {

    }

    public static VisitorTicketTracker getInstance() {
        if(instance == null) {
            instance = new VisitorTicketTracker(); // NOT THREAD-SAFE!
        }
        return instance; 
    }// Data access methods ...
}
 ```
 * Creating a reusable object the first time it is requested is a software design pattern known as _lazy instantiation_
 * The downside of lazy instantiation is that users may see a noticeable delay the first time a particular type of resource is needed

 ### Creating Unique Singletons

 _Thread safety_ is the property of an object that guarantees safe execution by multiple threads at the same time.

```java
 public static synchronized VisitorTicketTracker getInstance() { 
     if(instance == null) {
         instance = new VisitorTicketTracker();
     }
    return instance; 
}
```
#### singletons with Double‐Checked locking
* _Synchronization_ is only needed the first time that the object is created.

```java
private static volatile VisitorTicketTracker instance; 

public static VisitorTicketTracker getInstance() {
    if(instance == null) { 
        synchronized(VisitorTicketTracker.class) {
            if(instance == null) {
                instance = new VisitorTicketTracker();
            } 
        }
    }
    return instance; 
}
```

### Creating Immutable Objects

The _immutable object pattern_ is a creational pattern based on the idea of creating objects whose state does not change after they are created and can be easily shared across multiple classes

### Applying an Immutable Strategy

1. Use a constructor to set all properties of the object.
2. Mark all of the instance variables `private` and `final`.
3. Don’t define any setter methods.
4. Don’t allow referenced mutable objects to be modified or accessed directly
5. Prevent methods from being overridden.

```java
import java.util.*

public final class Animal {
    private final String species;   
    private final int age;
    private final List<String> favoriteFoods;
    
    public Animal(String species, int age, List<String> favoriteFoods) {
         this.species = species;
         this.age = age;
         if(favoriteFoods == null) {
             throw new RuntimeException("favoriteFoods is required");
         }
         this.favoriteFoods = new ArrayList<String>(favoriteFoods);
    }

    public String getSpecies() { 
        return species;
    }
    
    public int getAge() { 
        return age;
    }
    
    public int getFavoriteFoodsCount() { 
        return favoriteFoods.size();
    }
    public String getFavoriteFood(int index) { 
        return favoriteFoods.get(index);
    } 
}
```
#### handling mutable Objects in the Constructors of immutable Objects
Consider the following in the constructor:
```java
this.favoriteFoods = favoriteFoods;
```
- the caller that creates the object is using the same reference as the immutable object, which means that it has the ability to change the List!

### “Modifying” an Immutable Object
