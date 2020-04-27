String - Stringbuilder- Array-Arraylist
---

The String Class
---
- String are immutable object 
- it can never be changed
- 16 bit unicode character

Java Uses
---
- JVM set aside a special area of memory called the String constant pool
- when the compiler encounter a string literal,it checks the pool to see if an identical String already exist.if a match is found, the - reference to the new literal us directly to the existing String.No new String literal is created
- String class is final
- Nobody can override the behavior of any String method

```java
Sting S = "ABC"; // created one String object and one reference variable
String s = new String("abc"); //creates two objects //and one reference variable
```

Important method in the String Class
---
`char charAt(int index)`: returns the character located at the specific index
```java
String x = "airplane";
System.out.println(x.charAt(2)) //"r"
```
`public String concat(String s)`: appends one string to the end of another 

`public String subString(int begin, int end)`: 
   begin - starting location(zero based)
end - nth position
 	    - not zero based
 	    - 7 means 7 position
"hamburger".substring(4, 8) returns "urge"

Stringbuilder
----
- exactly same as StringBuffer 
- is faster because its method aren't synchronized
- not threadsafe

```java
Stringbuilder sb = new StringBuilder("abc");
sb.append("def").reverse().insert(3,"---")
System.out.println(sb); 	=> "fed---cba"
```

all of the StringBuilder methods operate on the value of the StringBuilder object invoking the method

Constructs of StringBuilder
---

```java
StringBuilder()
StringBuilder(Charsequence sq)
StringBuilder(int capacity)
StringBuilder(String str)
```

Mehtods of StringBuilder class
----

```java
public StringBuilder append(String s)
public StringBuilder delete(int start,int end):
StringBuilder sb = new StringBuilder("0123456789");
sb.delete(4,6); //01236789
```
start : zero based
end: one based(start to end -1)

`public StringBuilder insert(int offset,S)` :

```java
StringBuilder sb = new StringBuilder("0123456789");
sb.insert(4,"---"); //0123---4567 //one based
```
`public StringBuilder reverse() `:
```java
StringBuilder sb = new StringBuilder("A man");
sb.reverse(); // nam A
```

Arrays
----

Arrays are objects in java
---

```java
Thread [] thresds //legal 
Thread threads [] //legal but bad 
String [][][] names ; //multidimensional
String [] names []; //legal
```
It is never legal to include the size of an array in your declaration

`int [6] scores; // not legal //not compile`

Conducting Arrays
---
One dimensional array
---
`int [] testScores = new int[4];`

arrays must always be given a size at the time they are constructed

`int carList = new int[]; //will not compile`

any code that uses the keyword `new` will cause the class constructor and all superclass constructor to run.

Constructing multidimensional array
---
`int [][] myArray = new int [3][]`
- arrays of array
- JVM needs to know only the size of the object assigned to the variable myArray

Initializing the array
---

```java
int [] [] myArray = new int [3][];
myArray[0] = new int [2];
myArray[1] = new int [3]
```
ArrayIndexOutOfBoundException
---
- tries to access an out of range array index

Declaring,constructing and Initializing on One line
---

```java
int [] dots = {6,x,8};
int [][] scores = {{5,2,4,7},{9,2},{3,4}};
```
- created four(04) objects on the heap

Anonymous Array
---

```java
int [] testScores = new int[]{4,7,2};
jit.takesArray({1,2,3}) ;//illegal
jit.takesArray(new int [] {1,2,3}) ;//legal - called on the fly array
cat [][] myCats = {{ new Cat("Frodo"),new Cat("Zeus")},{ new Cat("Logan"),new Cat("Bob",new Cat("Bilbo")}};
```

Eight objects are created
---
1 2D `Cat [][]` array object
2 `Cat[]` array object
5 `Cat` Object

`new Object[3]{ null,new Object(), new Object()} //not legal;size must not specified`
- do not specify a size when using anonymous array creation syntax

Legal Array Elements Assignment
---
- primitive array can accept any value that can be promoted implicitly to the declared type of the array
- an int array can hold any value that can fit into a 32-bit int variable

```java
	int [] weightList = new int[5];
	byte b = 4;
	char c = 'c';
	short s = 7;
	weightList[0] = b; //ok, byte is smaller than int
    weightList[1] = c;
    weightList[2] = s;
```
Arrays of Object reference
---
- if the declared array type is a class,you can put objects of any subclass of the declared type int the array
	`Car [] myCars = { new Subaru(), new Car(), new Ferrari()}`
- if the array is declared as an interface type, the array elements can refer to any instance of any class that implements the declared interface.

```java
interface Sporty{
	void beSporty();  
}   
Sporty [] sprotyThings;
```
- Any object that passes the IS-A test for the declared array type can be assigned to an element of that array.

Array reference assignment for one-dimensional array
---
* if you declared an int array,the reference variable you declared can be reassigned to any int array,but variable can not be reassigned to anything that is not an int array,including an int value.

```java
int[] splats;
int dats = new int[4];
char [] letters = new char[5];
spalts = dats; //legal
splats = letters; //illegal
```
* Arrays that can hold object references, an opposed to primitives,aren't as restrictive

```java
Car [] cars;
Honda [] cuteCars = new Honda[5];
cars = cuteCars; //OK because Honda is a type of Car
Beer [] beers = new Beer[77];
cars = beers; //NOT OK,Beer is not a type of Car
```
* The rules for Array assignment apply to interface as well as classes
* you can not reverse the legal assignment

Array reference assignment for multidimensional array
---
* when you assign as array to a previously declared array reference, the array you're assigning must be in the same dimension as the reference you're assign to it

```java
int [] bolts;
int [][] squeegees = new int[3][];
bolts = squeeges; //NOT OK
int [] blocks = new int[6]
bolts = blocks; //OK
```
Q/A
---
- In an array creation expression, there may be one or more dimension expressions, each within brackets. Each dimension expression is fully evaluated before any part of any dimension expression to its right.

```java
int i = 4;
int ia[][][] = new int[i][i = 3][i]; // [4][3][3]
```
Note that if evaluation of the expression to the left of the brackets completes abruptly,
no part of the expression within the brackets will appear to have been evaluated.

- Note that an array of integers IS an Object :
```java
Object obj = new int[]{ 1, 2, 3 }; // is valid.
But it is not an Array of objects.
Object[] o = new int[10]; // is not valid.
```
- If the array reference expression produces null instead of a reference to an array, then a NullPointerException is thrown at runtime, but only after all parts of the array reference expression have been evaluated and only if these evaluations completed normally.

```java
getArray()[index=2]++;
getArray(){return null}
```  
 	   

ArrayList
----
- java.util package
- implement list interface
- index are zero based

When you pass an object reference to either `System.out.print()` or `System.out.println()`, you are telling them to invoke that objects's `toString()` method.

Arraylist and duplicates
---
* ArrayList can have duplicates
* can only hold object reference

  `myArrayList.add(7); //legal`
int being autoboxing here to Integer object

ArrayList methods
---
`add(element)` : add elements to the end

`add(index,element)` : add this element at the index point and shift the remaining element back

`clear()` : removes all elements from the arraylist

`boolean contains(element)`:

`Object get(index)`: 

`int indexOf(object)`: 

`remove(index)`: 

`remove(object)`: 

`int size()`: 

Encapsulation for reference variable
---

```java
Class A {
	ArrayList<Object> list  = new ArrayList<Object>();
	public Object getList(){
		return list;
	}
}
```

is actually not encapsulation


Extra
---
* There is no `reverse()` method in String class.

In an array creation expression, there may be one or more dimension expressions, each
within brackets. Each dimension expression is fully evaluated before any part of any
dimension expression to its right.

```java
int i = 4;
int ia[][][] = new int[i][i = 3][i];
System.out.println( ia.length + ", " + ia[0].length+", "+ ia[0][0].lenght)
 = > 4,3,3
```
