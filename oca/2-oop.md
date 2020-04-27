# Object Orientation

Encapsulation
---
The ability to make change in your implementation code without breaking the code of others who use your code is a key benefit of encapsulation.

Encapsulation - How?
---
- Keep instance variable protected 
- Make public accessor method
- For accessor methods, use the most common coding convention

Inheritance and Polymorphism
---
- Every class in java is a subclass of class object
The most common reason to use inheritance 
    - To promote code reuse 
    - To use polymorphism
- the methods you can call on a reference are totallydependent on the declared type of the variable, no matter what actual object is, that the reference referring to.  

IS-A and HAS-A relationship
---

IS-A
----
- This is a type of thing”
- Through the keywords extends and implements
- A class is said to be “a type of” anything further up in its inheritance tree

HAS-A
---
- Are based on usage, rather than inheritance
- “Specialized classes can actually help reduce bugs”

Polymorphism
-----
- All java objects are polymorphic
- Pass IS-A test for their own type and for class object
- A reference variable can be only one type, once declared, type can never change
- A reference variable can refer to any subtype of the declared type
- A reference variable can be declared as a class type or an interface type
- Java doesn’t support multiple inheritance
- The method invocations allowed by the compiler are based on the declared type of the reference regardless of the object type
- Even though the compiler only knows about the declared reference type, the java virtual machine(JVM) at runtime knows what the object really is
- Polymorphic method invocations apply only to instance method,not static method,not variable
- Only overridden instance method are dynamically invoke based on real objects type

Overriding/overloading
---

Overriding method
---
- Key benefit of overriding is the ability to define behavior that’s specific to a particular subclass type
- Abstract methods must be implemented by the concrete subclass 
- The overriding method can not have a more restrictive access modifier than the method being overridden
You can’t override a method marked public, and make it protected

Rules for overriding
------
- The argument list must exactly match that of the overridden method. If they don’t match, you can end up with an overloaded method you didn’t intend
- The return type must be same,as or subtype of, the return type declared in the original overridden method in the superclass
- Access level can’t be more restrictive
- The overriding method must not throw checked exception that are new or broader than those declared by the overriding method
- You can’t override a method marked /final
- You can’t override a method marked static

Invoking a superclass version of an overridden method
----
- Use super keyword
- If the supertype version declares a checked exception, but the overriding subtype method does not,the compiler still thinks you are calling a method that declares an exception

```java
class Animal{
	public void eat() throws Exception{}
}

class Dog{
	public void eat(){} //no exception
	public static void main(String [] args){
	Animal a = new Dog();
	Dog d = new Dog();
    d.eat();
    a.eat();//compiler error// unreported exception 
    }
}
```

Illegal Overriding
---

```java
public class Animal{
	public void eat(){}
}
```
| Illegal Overriding                    |        Problem| 
|---------------------------------------|:---------------------:|
| `public void eat()`                   | Access modifier is more restrictive|   
| `public void eat() throws IOexception`| Declares a checked exception not defined by superclass version |
| `public void eat(String s)`           | A legal overload,not an override,because argument list change  |
| `public String eat()`                 | Not an override because of the return type, and not an overload either because there’s no change in the argument list|                           

Overload methods
-------
- rules of overload methods
- Must change the argument list
- CAN change the the return type
- CAN change the access modifier
- CAN declare new or broader checked exception
- Can be overloaded in the same class or in a subclass

Legal overloads
---
```java
 public void changeSize(int size,String name,float pattern){}
```

Legal overloaded method
-----

```java
public void changeSize(int size,String name){}
public void changeSize(int size,float pattern){}
public void changeSize(float pattern,String name) throws IOException{}
```

Invoking Overloaded methods
-----
- The reference type(not the object type) determines which overloaded method is invoked
- Which overriden version of the method to call is decided at runtime based on object type, but which overloaded version of the method to call is based on the reference type of the argument passed at compile time.

Can main() be overloaded? 
YES

```
public class DuoMain{
	public static void main(String [] args){
        main(1);	
    }

static void main(int i){
	System.out.println(“Overload main”);
}
}
```

Polymorphism in overloaded and overridden methods
---------
```java
public class Animal{
	public void eat(){}
}

public class Horse extends Animal{
public void eat(){}
public void eat(String s){}
}

Animal a = new Horse();
a.eat(“carrots”); // compiler error
```

Difference between overloaded and overridden method
---
|| Overloaded method | Overridden method |
|:---:|:-------------------:|:-------------------:|
|argument(s)|Must change|Must not change|
|Return types|Can change|Can’t change,except for covariant returns|
|Exception|Can change|Can reduce or eliminate.must not throw new or broader checked exception|
|Access|Can change|Must not more restrictive|
|Invocation|Reference type determines which overloaded version is selected|Object type determines which method is selected.Happens at runtime|

Casting
------
Downcast - casting down the inheritance to a more specific 
```java
class
 Animal a = new Dog();
((Dog) a).doDogStuff();
```
Compiler needs all those parentheses; otherwise it thinks its been handed an incomplete statement.

Implementing an interface
----
A non abstract implementation class must do the following:

- Provide concrete(nonabstract) implementation for all methods from the declared interface
- Follow all rules for legal overrides,such as the following:
    - Declare no checked exception on implementation  methods other than those declared by the interface method or subclass of those declared by the interface method
    - Maintain the signature of the interface method and main the same return type(or a subtype)
    - If the implementation class is abstract,it can simply pass the buck to its first complete subclass
    - An interface can itself extend another interface but it can never implement anything
    - An interface can extend multiple interface

Legal Return types
---
Return types on overloaded methods
---
```
public class Foo{
	void go(){}
 }
public class Bar extends Foo{
	String go(){}  // not legal; can’t change only return type
 }

```

Overriding and return types and covariant returns
---
You’re allowed to change the return type in the overridden method as long as the new return type is a subtype of the declared return type of the overridden method

Returning a value
---
- You can return null in a methods with an object reference return type
- An array is a perfectly legal return type
- In a method with a primitive return type, you can return any value or variable that can be implicitly converted to the declared return type

```java
public int foo(){
	char c = ‘c’;
	return c; // char is compatible with int
}
```
- In a method with primitive return type,you can return any value or variable that can be explicitly cast to the declared return type.

```java
public int foo(){
	float f = 32.5f;
	return (int) f;
}
```
- You must not return anything from a method with a void return type.
- In a method with an object reference return type,you can return any object type that can be implicitly cast to the declared return type.

```java
public Object getObject(){
	int [] nums = {1,2,3};
	return nums;
} 
```
- Method that declare an abstract class or interface return type,and know that any object that passes the IS-A test can be returned from that method.

Constructor and Instantiation
---
- Objects are constructed
- You can’t make a new object without invoking not just the constructor of the object's actual class type,but also the constructor of each of its superclass.
- Every class including abstract classes MUST have a constructor
- Have no return type,must exactly match the class name

Constructor Chaining
---
```java
class Animal{}
class Horse extends Animal{}
Horse h = new Horse();

Object()
Animal() call super()
Horse() calls super()
main() calls new Horse()
```
Rules for Constructor
---
- Constructor can use any access modifier including private
- It’s legal(but stupid) to have a method the same name as the class,but that doesn’t make it a constructor
- If you don’t type a constructor into your class code,a default constructor will be automatically generated by the compiler
- The default constructor is always a no-ar constructor
- Every constructor has,as its first statement,either a class to an overloaded constructor(this()) or a call to the superclass constructor(super()) although remember that this call can be inserted by the compiler
- You can not make a call to an instance method or access an instance until after the super constructor runs

```java
Class Horse{
	Horse(){}
	void stuff(){
	    Horse(); // illegal
    }
}
```

Determine whether a default constructor will be created
---
- When you don’t write any constructor in your class
- Default constructor has the same access modifier as the class
- The default constructor has no argument
- The default constructor includes a no-arg call to the super constructor
- If your superclass doesn’t have a no ar constructor,you must type a constructor in your class(the subclass) because you need a place to put in the call to super() with the appropriate arguments.

Constructor are never inherited
---
Overloaded Constructor
---
Key rule: the first line in a constructor must be a call to super() or a call to this();
 - A constructor can never have both a call to super() and a call to this() 

Problem
---

```java
class A {
	A(){
        this(“Foo”);
    }
    A(String s){
        this();
    }
}
```
- The stack explores

By putting all the other constructor work in just one constructor, and then having the other constructor invoke it,you  don’t have to write and maintain multiple version of that other important constructor code
 
Initialization blocks
---
Initialization blocks run when the class is first loaded(a static initialization block) or when an instance is created(an instance initialization).

```java
class smallInt {
  	static int x;
Int y;
static{ x= 7;} //static init block
{ y = 8;} //instance init block
}
```

- A static initialization blocks runs once,when the class is first loaded
- A static initialization blocks runs once,when the class is first loaded
- Instance init blocks runs after the constructor’s call to super();
- Init blocks execute in the order in which they appear

If you make a mistake in your static init block the JVM can throw an `ExceptionInitialization` Error
---

```java
class InitError{
static int [ ] x = new int[4];
	static{  x[4] = 5;} //bad array index
}
```

Statics
---
- Variable and methods marked static belong to the class, rather than to any particular instance.
- A static method can’t access a nonstatic variable because there is no instance
- A static method can’t directly invoke a nonstatic method

Accessing static methods and variables
---
- Java language also allows you to use an object reference variable to access a static member
- Static member unaware of the particular instance used to invoke the static member
- Static method can’t be overridden

```java
class Animal{
    static void doStuff(){
        System.out.println(“a”);
    }

Class Dog extends Animal{
	static void doStuff(){
		System.out.println(“d”);
}
}

public static void main(String [] args){
Animal [] a = { new Animal(),new Dog(),new Animal() }

for(int i = 0; i<a.length; i++ ){
a[i].doStuff(); 
}
 Dog.doStuff();
 }
}
 => a a a d
```

Overloading Rules
--------
1. The compiler always tries to choose the most specific method available with least
number of modifications to the arguments.
2. Java designers have decided that old code should work exactly as it used to work
before boxing-unboxing functionality became available.
3. Widening is preferred to boxing/unboxing (because of rule 2), which in turn, is preferred over var-args.

```java
class TestClass{
void probe(int... x) { System.out.println("In ..."); } //1
void probe(Integer x) { System.out.println("In Integer"); }  //2
void probe(long x) { System.out.println("In long"); } //3
void probe(Long x) { System.out.println("In LONG"); } //4

public static void main(String[] args){
Integer a = 4; new TestClass().probe(a); //5
int b = 4; new TestClass().probe(b); //6
}	
}
=> In Integer ,In long
```

Extras
----

The concept here is that an overriding method cannot make the overridden method more private.

The access hierarchy in increasing levels of accessibility is:
`private->'no modifier'->protected->public` ( public is accessible to all and private is accessible to none except itself.) 
									
private is not a valid access modifier for a top level class.			
private can be applied to an inner class. 

```java
package p; 
private class TC extends java.util.HashMap{					
public TC(){
 super(100);				
System.out.println("TC created"); 
}			
}

```

Extra
----
Class initialization
------------------------
The order of initialization of a class is:
1. All static constants, variables and blocks.(Among themselves the order is the order
in which they appear in the code.)
2. All non static constants, variables and blocks.(Among themselves the order is the
order in which they appear in the code.)
3. Constructor.

Static and Blocks
------------------------
1. Static blocks of the base class (only once, in the order they appear in the class).
2. Static blocks of the class.
3. Non-static blocks of the base class.
4. Constructor of the base class.
5. Non-static blocks of the class.
6. Constructor of the class.
7. Derived class's static or non-static blocks are not executed if that class is not being


Overriding
----------------------
```java
class Car{
  public int gearRatio = 8;
}

class SportsCar extends Car{
 public int gearRatio = 9;
}

Car c = new SportsCar();
Println c.gearRatio 
=> 8
```
The concept is : variables are shadowed and methods are overridden.Method to be executed depends on the class of the actual object the variable is referencing to. Here, `c `refers to object of class `SportsCar` so `SportsCar`'s `accelerate()` is selected.

Class/Interface type Initialization
-------------------------------------------
As per JLS 12.4.1 - A class or interface type T will be initialized immediately before
the first occurrence of any one of the following:
T is a class and an instance of T is created.
T is a class and a static method declared by T is invoked.
A static field declared by T is assigned.
A static field declared by T is used and the field is not a constant variable.
T is a top-level class, and an assert statement lexically nested within T is executed.

```java
class Main {
  public static void main(String[] args) {
  
		System.out.println(A.V );    
  }
}

class A{
	 static  int V = 0;
	static{
		System.out.println("static A");
	}
}

=> static A
    0
```






































