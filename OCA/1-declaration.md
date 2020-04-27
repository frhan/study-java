
Declaration and Access Control
-------------------------------

Java Refresher
--------------
* class
* object
* State(instance variable)
* Behavior(Methods)

Inheritance
---
* allows code defined in one class to be reused in other classes.
* The superclass knows nothing of the classes that inherit from it

Interface
---------
* 100-percent abstract superclass that defines the methods a subclass must support, but not how they must be supported

Identifiers and Keywords
------------------------
* must start with a letter,a currency character($), or connecting character _
* can't start with digit
* after first character,identifier can contain any combination of letters,currency character,connecting character or numbers

**illegal**
```java
  int e#;
  int .f;
  int 7g;
```
Define Classes
------------
**Source File Declaration Rules**

* There can be only one public class per source code file.
* If there is a public class in a file, the name of the file must match the name of the public class.
* import and package statements apply to all classes within a source code file.
* A file can have more than one nonpublic class.

**Compiling with javac**
```java
javac [options] [source files]
```
**Launching Applications with java**

```java
java [options] class [args]
java -version MyClass x 1
```

**Using _public static void main(String[ ] args)_**

_main()_ is the method that the JVM uses to start execution of a Java program.

__public static void main(String[] args)__

Other versions of _main()_ with other signatures are perfectly legal, but they're treated as normal methods.

**The following are all legal declarations for the "special" main() :**

```java
static public void main(String[] args)
public static void main(String... x)
static public void main(String bang_a_gong[])
main() can be overloaded
```

**Import Statements and the Java API**

If you see a reference to a class you're not sure of, you can look through the entire package for that class

**Static Import Statements**

Static imports can be used when you want to "save typing" while using a class's static members.

After static imports:

```java
import static java.lang.System.out;
import static java.lang.Integer.*;
public class TestStaticImport {
 	public static void main(String[] args)
 		out.println(MAX_VALUE);
 		out.println(toHexString(42));
     }
}
```

**Here are a couple of rules for using static imports:**

* You must say import static ; you can't say static import .
* Watch out for ambiguously named static members.Integer class and the Long class, referring to MAX_VALUE will cause a compiler error
* You can do a static import on static object references, constants (remember they're static and final ), and static methods.

sometimes you can use the wildcard character * to do some simple searching for you. (You can search within a package or within a class.)

``` java
import java.*; // Legal, but this WILL NOT search across packages.
```

**Class Declarations and Modifiers**
* every class, method, and instance variable you declare has an access control, whether you explicitly type one or not

* a class can be declared with only public or default access; the other two access control levels don't make sense for a class

Enums
---
`enum CoffeeSize { BIG, HUGE, OVERWHELMING } // this cannot be // private or protected`
an `enum` that isn't enclosed in a class can be declared with only the `public` or `default` modifier, just like a non-inner class. 

```java
public class CoffeeTest1 {
    public static void main(String[] args) {
      enum CoffeeSize { BIG, HUGE, OVERWHELMING } // WRONG! Cannot
       								// declare enums
       Coffee drink = new Coffee();
       drink.size = CoffeeSize.BIG;
  }					
} 
```
Declaring Constructors, Methods, and Variables in an `enum` 
- an enum really is a special kind of class					
- can add constructors, instance variables, methods, and something really strange known as a constant specific class body. 
					
```java
enum CoffeeSize {
    // 8, 10 & 16 are passed to the constructor
    BIG(8), HUGE(10), OVERWHELMING(16);
   CoffeeSize(int ounces) {
      this.ounces = ounces;
   }
   
    private int ounces;
    public int getOunces() {
      return ounces;
    }
} 
```
- Every enum has a static method, values(), that returns an array of the enum's values in the order they're declared 
				
- You can NEVER invoke an enum constructor directly. The enum constructor is invoked automatically, with the arguments you define after the constant value. 	
- You can define more than one argument to the constructor, and you can overload the enum constructors .
    `BIG(8, "A")` 

you can define something really strange in an enum that looks like an anonymous inner class . It's known as a constant specific class body, and you use it when you need a particular constant to override a method defined in the enum. 

```java
enum CoffeeSize {
     BIG(8),
     HUGE(10),
     OVERWHELMING(16) {
					// start a code block that defines
					// the "body" for this constant
    public String getLidCode() { 
      // override the method
			 // defined in CoffeeSize
 	    return "A"; 
   }				
  };    // the semicolon is REQUIRED when more code follows
					
CoffeeSize(int ounces) {
   this.ounces = ounces;
}

private int ounces;
public int getOunces() {
  return ounces;
}
public String getLidCode() { // the default value we want to
				 // return for CoffeeSize constants
return "B"; 
			
 }
}	
```

Extra
---

Package
-------------
- If you are not importing a class or package of the class,you need yo use the class’s fully occupied qualified name while using it.
- Using a fully qualified class name always works irrespective of whether you import the package or not.

Dangling else Problem
-------------------------------
 -  the  `else` keyword always associate with the nearest possessing of keyword that does not cause a syntax error.

Chained Initialization
------------------------
Java does not allow chained initialization in declaration.

```java
int a, b, c;
 a = b = c = 100; // no problem
int a = b = c = 100; // error
```

Static Import
------------------
Syntax for importing static fields is: 
`import static <package>.<classname>.*`;

 or

`import static <package>.<classname>.<fieldname>`;


Package
---

Apparent Hierarchies of Packages
---

At first, packages appear to be hierarchical, but they are not.

For example, the Java API includes a `java.awt` package, a `java.awt.color` package, a `java.awt.font` package, and many others that begin with `java.awt`. However, the `java.awt.color` package, the `java.awt.font` package, and other `java.awt.xxxx` packages are not included in the `java.awt` package. The prefix `java.awt` (the Java Abstract Window Toolkit) is used for a number of related packages to make the relationship evident, but not to show inclusion.

Importing `java.awt.*` imports all of the types in the `java.awt` package, but it does not import `java.awt.color`, `java.awt.font`, or any other `java.awt.xxxx` packages. If you plan to use the classes and other types in `java.awt.color` as well as those in `java.awt`, you must import both packages with all their files:

```java
import java.awt.*;
import java.awt.color.*;
```

Name Ambiguities
----
If a member in one package shares its name with a member in another package and both packages are imported, you must refer to each member by its qualified name. For example, the `graphics` package defined a class named `Rectangle`. The `java.awt` package also contains a `Rectangle` class. If both `graphics` and `java.awt` have been imported, the following is ambiguous.

`Rectangle rect;`

In such a situation, you have to use the member's fully qualified name to indicate exactly which Rectangle class you want. For example,

`graphics.Rectangle rect;`


The Static Import Statement
---
frequent access to `static final` fields (constants) and `static` methods from one or two classes. 

The `java.lang.Math` class defines the `PI` constant and many static methods, including methods for calculating sines, cosines, tangents, square roots, maxima, minima, exponents, and many more. For example,
```java
public static final double PI 
    = 3.141592653589793;
public static double cos(double a)
{
    ...
}
```
Ordinarily,
`double r = Math.cos(Math.PI * theta);`

static import statement to import the static members of `java.lang.Math`

imported either individually:

`import static java.lang.Math.PI;`

or as a group:

`import static java.lang.Math.*;`

`double r = cos(PI * theta);`

Obviously, you can write your own classes that contain constants and static methods that you use frequently, and then use the static import statement. For example,

```java
import static mypackage.MyConstants.*;
double r = cos(PI * theta);
```