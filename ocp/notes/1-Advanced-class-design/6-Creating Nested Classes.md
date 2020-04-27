# Creating Nested Classes

A _nested class_ is a class that is defined within another class. 
A _nested class_ that is not static is called an _inner class_. 

There are four of types of nested classes:

* A member inner class is a class defined at the same level as instance variables. It is not static. Often, this is just referred to as an inner class without explicitly saying the type.
* A local inner class is defined within a method.
* An anonymous inner class is a special case of a local inner class that does not have a name.
* A static nested class is a `static` class that is defined at the same level as static variables.

### Member Inner Classes
A _member inner class_ is defined at the member level of a class (the same level as the methods, instance variables, and constructors).

* Can be declared public, private, or protected or use default access Can extend any class and implement interfaces
* Can be abstract or final
* Cannot declare static fields or methods
* Can access members of the outer class including private members

```java
public class Outer {
    private String greeting = "Hi";
    protected class Inner {
        public int repeat = 3;
        public void go() {
            for (int i = 0; i < repeat; i++)
            System.out.println(greeting); 
        }
    }
    
    public void callInner() { 
        Inner inner = new Inner(); 
        inner.go();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        //outer.callInner(); 
        Inner inner = outer.new Inner(); // create the inner class
        nner.go();
    } 
}
```
_Inner classes_ can have the same variable names as outer classes.
```
System.out.println(x); 
System.out.println(this.x);
System.out.println(B.this.x); 
System.out.println(A.this.x);
```
#### Private interfaces

```java
public class CaseOfThePrivateInterface { 
    private interface Secret {
        public void shh(); 
    }
    class DontTell implements Secret { 
        public void shh() { }
    }
}
```
* The interface itself does not have to be public, though.
* Just like any inner class, an inner interface can be private

### Local Inner Classes
A local inner class is a nested class defined within a method. 

 Local inner classes have the following properties:
 
 * They do not have an access specifier.
 * They cannot be declared _static_ and cannot declare _static_ fields or methods.
 * They have access to all fields and methods of the enclosing class.
 * They do not have access to local variables of a method unless those variables are `final` or effectively `final`. 

 - If the code could still compile with the keyword final inserted before the local variable, the variable is effectively final.

```java
 public class Outer {
     private int length = 5; 
     public void calculate() {
         final int width = 20; 
         class Inner {
             public void multiply() { 
                 System.out.println(length * width);
            } 
         }
         Inner inner = new Inner();
         inner.multiply(); 
    }
    
    public static void main(String[] args) { 
        Outer outer = new Outer(); 
        outer.calculate();
    } 
}
```
### Anonymous Inner Classes
An _anonymous inner class_ is a local inner class that does not have a name.
It is declared and instantiated all in one statement using the `new` keyword.

The _anonymous inner class_ is the same whether you implement an `interface` or `extend` a class!

```java

public class AnonInner { 
    interface SaleTodayOnly {
        int dollarsOff(); 
    }
    
    public int admission(int basePrice) { 
        SaleTodayOnly sale = new SaleTodayOnly() {
            public int dollarsOff() { return 3; } 
        };
        return basePrice - sale.dollarsOff();
    }
}
```

### Static Nested Classes

A _static nested class_ is a static class defined at the member level.

It can be instantiated without an object of the enclosing class, so it can’t access the instance variables without an explicit object of the enclosing class.

In other words, it is like a regular class except for the following:
* The nesting creates a namespace because the enclosing class name must be used to refer to it.

* It can be made private or use one of the other access modifiers to encapsulate it. 

* The enclosing class can refer to the fields and methods of the static nested class.

```java
public class Enclosing { 
    static class Nested {
        private int price = 6; 
    }
    public static void main(String[] args) { 
    Nested nested = new Nested(); 
    System.out.println(nested.price);
    } 
}
```

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-07%20at%2011.22.16%20AM.png)