Java Operators
---

Assignment operators
---
- when assigning a value to a primitive,size matters
- reference variable isn't an object,it's a way to get to an object
- when assigning a value to a reference variable, type matters


Compound Assignment Operators
---
- +=, -=,*=, /=
- and / are higher precedence than + and -

Relational Operator
---
<,<=,>,>=,==,!=
always result in a boolean(true or false) value 
its also legal to compare characters primitive with any number
- java use unicode value of the character as the numerical value,for comparison

"Equality" Operators
---
- we can't compare incompatible type
- four different types
    - numbers
    - Characters
    - Boolean
    - Object Reference

Equality for primitives
---

`(5.0 == 5L) => true`

Equality for reference variable
---

reference variable can be tested to see if they refer to the same object by using == operator

Equality for String and java.lang.object.equals()
---
`equals()` methods is used to determine if two objects of the same class are "meaningfully equivalent"

equals() methods in class Object
---
- if two references point to same object,the equals method return true
- if two reference point to different objects,event if they have same value,the method will return false

The equals method in class String
---
compare two Strings, it will return true if the strings have the same value, and it will return false if the string have different value

instanceof comparison
---
- used for IS-A test
- the use of the instanceof operator protects the program from attempting an illegal downcast
- can test a object reference against its own class type or any of its superclass
- an object is said to be of a particular interface type( meaning it will pass the instanceof test) if any of the object's superclasses implement the interface
- it's legal to test whether the null reference is an instance of a class

 `boolean b = null instanceof String; // false`	     

instanceof Compiler Error
---
you can't use the instanceof operator to test across two different class hierarchies
 
```java
 class Cat {}
 class Dog {
     public static void main(String [] args){
        Dog d = new Dog();
 	 	if(d instanceOf Cat){ // compilation erroe
          }
 	}    
}
```
An array is always an instanceof `Object.Any` array

Arithmetic Operators
---
*, / and % operators have higher precedence than the + and - operators

String concatenation operator
---
- gets interesting when you combine numbers with strings

```java
String a = "string";
int b = 3;
int c = 7;
System.out.println(a+b+c) => string37
System.out.println(a+(b+c)) => string10
```
- if either operand is a string, the + operator become a string concatenation operator.if both operands are numbers, the + operator is the additional operator.

```java
int b =2;
System.out.println(" "+b+3) => 23
System.out.println(b+3) => 5
```
Increment and Decrements operator
---
++(prefix and postfix)
--(prefix and postfix) 
`final` variables can't be changed,the increment and decrement operators can't be used with them and compiler error

```java
final int x = 5; //compiler error
int y = x++;
```

Conditional operator
---
x = boolean expression ? value to assign if true: value to assign if false;
we can even nest conditional operators into one statement    
    `String status = numOfPets < 4 ? "Pet count OK" : (sizeOfYard > 8) ? "Pet limit on edge": "Two many pets"`


Logical operators
###
short circuit logical operators
---

&& and ||
- evaluates only boolean values
- it doesn't waste time on pointless operation
- the || and && operators work only with boolean operands

not short circuit logical operators
---
& and |

they aren't short circuit, they evaluates both sides
Logical operators(^ and !)
- ^ exclusive-OR(XOR)
- ! boolean invert

^ - non short circuit
   - exactly one operand must true
   





