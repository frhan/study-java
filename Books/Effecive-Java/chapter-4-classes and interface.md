Classes and Interfaces
---
Item 13: Minimize the accessibility of classes and members:
--

* make each class or member as inaccessible as possible
* If a top-level class or interface can be made package-private, it should be
* If a package-private top-level class (or interface) is used by only one class, consider making the top-level class a private nested class of the sole class that uses it.
* After carefully designing your class’s public API, your reflex should be to make all other members private
* A protected member is part of the class’s exported API and must be supported forever
* Instance fields should never be public
* classes with public mutable fields are not thread-safe.
* static fields -
 * You can expose constants via public static final fields
 * It is critical that these fields contain either primitive values or references to immutable objects
 * it is wrong for a class to have a public static final array field, or an accessor that returns such a field

There are two ways to fix the problem

*
 ```java
   private static final Thing[] PRIVATE_VALUES = { ... };
   public static final List<Thing> VALUES =
       Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
 ```
*
 ```java
 private static final Thing[] PRIVATE_VALUES = { ... };
   public static final Thing[] values() {
       return PRIVATE_VALUES.clone();
   }
 ```
* you should always reduce accessibility as much as possible.
*  you should prevent any stray classes, interfaces, or members from becoming a part of the API
* Ensure that objects referenced by public static final fields are immutable.

Item 14: In public classes, use accessor methods, not public fields
--

```java
// Degenerate classes like this should not be public!
   class Point {
       public double x;
       public double y;
   }
```

* if a class is accessible outside its package, provide accessor methods,to preserve the flexibility to change the class’s internal representation.
* if a class is package-private or is a private nested class, there is nothing inherently wrong with exposing its data fields


Item 14: In public classes, use access or methods, not public fields
--------
```java
// Degenerate classes like this should not be public!
   class Point {
       public double x;
       public double y;
   }
```
* these classes do not offer the benefits of encapsulation
* if a class is accessible outside its package, provide accessor methods
* if a class is package-private or is a private nested class, there is nothing inherently wrong with exposing its data fields
* public classes should never expose mutable fields.

Item 15: Minimize mutability
---------
* Immutable classes are easier to design, implement, and use than mutable classes

To make a class immutable, follow these five rules:
1. Don’t provide any methods that modify the object’s state
2. Ensure that the class can’t be extended
3. Make all fields final.
4. Make all fields private.
5. Ensure exclusive access to any mutable components

--
* Immutable objects are simple
* Immutable objects are inherently thread-safe; they require no synchronization.
*  immutable objects can be shared freely.
* An immutable class can provide static factories (Item 1) that cache frequently requested instances to avoid creating new instances when existing ones would do. All the boxed primitive classes and BigInteger do this.
*  you need not and should not provide a clone method or copy constructor (Item 11) on an immutable class.
* Not only can you share immutable objects, but you can share their inter- nals. For example, the BigInteger class uses a sign-magnitude representation internally. The sign is represented by an int, and the magnitude is represented by an int array. The negate method produces a new __BigInteger__ of like magnitude and opposite sign.
* Immutable objects make great building blocks for other objects,
* The only real disadvantage of immutable classes is that they require a separate object for each distinct value
* Classes should be **immutable** unless there’s a very good reason to make them mutable
* Immutable classes provide many advantages, and their only disadvan- tage is the potential for performance problems under certain circumstances.
* If a class can not be made immutable, limit its mutability as much as possible
*  make every field final unless there is a compelling reason to make it nonfinal.


Item 16: Favor composition over inheritance
---
* Unlike method invocation, inheritance violates encapsulation
*  a subclass must evolve in tandem with its super- class, unless the superclass’s authors have designed and documented it specifically for the purpose of being extended
* If the superclass acquires a new method in a subsequent release and you have the bad luck to have given the subclass a method with the same signature and a different return type, your subclass will no longer compile

SOLVE inheritance issue
----
* Instead of extending an existing class, give your new class a private field that references an instance of the existing class. This design is called composition because the existing class becomes a component of the new one

**Summary** :
inheritance is powerful, but it is problematic because it violates encapsulation.

Item 17: Design and document for inheritance or else prohibitit
---
* the class must document its self-use of overridable methods.
* a class may have to provide hooks into its internal workings in the form of judi- ciously chosen protected methods
* The only way to test a class designed for inheritance is to write subclasses.
* you must test your class by writing subclasses before you release it.
* Constructors must not invoke overridable methods, directly or indirectly.
* neither clone nor readObject may invoke an overridable method, directly or indirectly
* designing a class for inheritance places substantial limitations on the class.
* The best solution to this problem is to prohibit subclassing in classes that are not designed and documented to be safely subclassed.

Item 18: Prefer interfaces to abstract classes
---------
* Existing classes can be easily retrofitted to implement a new interface
* Interfaces are ideal for defining mixins.
 - Loosely speaking, a **mixin** is a type that a class can implement in addition to its “primary type” to declare that it provides some optional behavior .
 - For example, Comparable is a mixin interface that allows a class to declare that its instances are ordered with respect to other mutually comparable objects.
* Interfaces allow the construction of nonhierarchical type frameworks.
* Interfaces enable safe, powerful functionality enhancements
* You can combine the virtues of interfaces and abstract classes by providing an abstract skeletal implementation class to go with each nontrivial interface that you export.
  - For example, the Collections Framework provides a skeletal implementation to go along with each main collection interface: AbstractCollection, AbstractSet, AbstractList, and AbstractMap.

* It is far easier to evolve an abstract class than an interface
* Once an interface is released and widely implemented, it is almost impossible to change.
 - The best thing to do when releasing a new interface is to have as many programmers as possible implement the interface in as many ways as possible before the interface is frozen

Item 19: Use interfaces only to define types
---
* _constant interface_ : contains no methods; it consists solely of static final fields, each exporting a constant

``` java
public interface PhysicalConstants {
    // Avogadro's number (1/mol)
    static final double AVOGADROS_NUMBER   = 6.02214199e23;
    // Boltzmann constant (J/K)
}
```
 The **constant interface** pattern is a poor use of interfaces

* _noninstantiable utility_ class :

``` java
// Constant utility class
package com.effectivejava.science;
public class PhysicalConstants {
  private PhysicalConstants() { }  // Prevents instantiation

  public static final double AVOGADROS_NUMBER = 6.02214199e23; public static final double BOLTZMANN_CONSTANT = 1.3806503e-23;
  public static final double ELECTRON_MASS = 9.10938188e-31;
}
```

* If you make heavy use of the constants exported by a utility class, you can avoid the need for qualifying the constants with the class name by making use of the static import facility

 ``` java
 // Use of static import to avoid qualifying constants
   import static com.effectivejava.science.PhysicalConstants.*;
   public class Test {
      double atoms(double mols) {
        return AVOGADROS_NUMBER * mols;
   }
 ```
 **Summary** : interfaces should be used only to define types. They should not be used to export constants.
