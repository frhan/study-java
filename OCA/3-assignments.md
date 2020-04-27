Assignments
---

Stack and Heap
---
- instance variable and objects live on the heap
- local variables live on the stack

Literals,assignments and variables
---
- Java 7,numeric literals can be declared using underscore character(_) 

```java
 int pre7 = 10000; //legal
 int with7 = 1_00_000; //legal
 int i1 = _1_00_000; //illegal can't begin with an " _"
 int i2 = 1_0000_000; 
```       
Decimal literals
---
`int light = 343;`

Binary literals
---
`int b1 = 0B10101010;`

Octal Literals
---
`int seven = 07` 

Hexadecimal Literals
---
`int x = 0x0001`

- Accept lowercase/uppercase letters for extra digit
- Allowed upto 16 digit in a hexadecimal number

```java
int z = 0xDeadCafe
int z = 0xDEADCAFE
```

Floating point literals
---
`double d = 11130202.0123`

double(64bits)  - defautt
float(32bits) - must attach suffix f or F to the number

`float f = 23.405050500 // compiler error`

Boolean literals
---

```java
char a  = ‘a’;
char b = ‘@’;
char letterN = ‘\u004E’ //letter N
char c = (char) 7000;//legal
char d = (char) -98; //legal
char e = -29 //illegal
char f = 7000 ; //illegal
```
Characters are 16 bit unsigned integer under the hood.

Assignment Operators
---
`Button b = new Button();`
- A variable refering to an object is just that  - a reference variable
- A reference variable bit holder contain bit representing a way to get to the object

Primitive Assignment
---
```byte b = 27; //legal```
- Compiler automatically narrows the literal value to a byte
Integer or smaller expression always resulting in an int

```java
byte a = 3;
byte b = 3;
byte c = a+b; //won’t compile
byte c = (byte)(a+b);//legal
Int j,k=1,l,m = k+3; // legal; k is initiated before m uses it
Int j,k=m+3,l,m = 1 // illegal; m is not initiated before k uses it
```
Primitive Casting
---
- Cast can be implicit or explicit
- Implicit cast happens when we’re doing widening conversion
    - Putting smaller thing (say, a byte) into a higher container
- The large value into small container conversion is referred to as narrowing and requires an explicit cast

```java
int  a = 100;
long b = a; //implicit cast, an int value always fits into long

float a = 100.000f;
int b = (int) a; //explicit cast; the float could loss info
long l = 130L;
byte b = (byte) l;
System.out.println(b); //=> -126
```
- When the value being narrowed is too large for the type.The bit to the left for the lower 8 just ...go away

Assigning Floating Point Numbers
---
Every floating point literal is implicitly a double(64 bits),not a float
```java
float f = (float) 32.3
float f = 32.3f
```
Assigning a literal that is too large for the variable

`byte a = 128 // compiler error.possible loss of precision`

To find out the the value of a negative number using 2’s complement notation,you flip all of the bits and then add 1.

```java
byte b = 3
b +=7; //no problem adds 7 to b
byte b = 3;
b = b+7; //won’t compile
B = (byte) b+7; // compile
```

Assigning one Primitive variable to another primitive variable
---
Contents of the right-hand variable are copied

Reference variable Assignement
---
```java
Button b = new Button();
Long x = new Long(42);
Short s = new Short(“57”);
```

Scope
---
- Static variable have the longest scope.
    - They are created when the class is loaded
    - They survived as long as the class stays loaded in the JVM
- Instance variable are the most long lived
    - Created when a new instance is created
    - Live until the instance is removed
- Local variable are next
    - Live as long as their method remains on the stack

Block variable live only as long as the code block is executing

Scoping Errors
---
- Attempt to access a variable that is not in scope.

Common Scoping error
---
- Attempting to access an instance variable from a static context.

``` java 
Class ScopeErrors{
    int x = 5;
    public static void main(String [] args){
        x++; //won’t compile; x is an instance variable
    }
}
```

- Attempting to access a local variable from a nested method

```java
Class S{
    void go(){
    int y = 5;
    y++;	
    }

    void go2(){
        y++;	// won’t compile; y is local to go
    }
}
```
- Attempting to use a block variable after the code block has completed

```java
for(int i=0; i<9; i++){
   int  z = 0;
}
System.out.println(z);//cannot find symbol
```

Variable Initialization
---
Using a Variable or Array Element that is uninitialized and unassigned

Primitive and object type instance variable
---
Instance variable are initialized to a default value each time a new instance is created.

Default value
---
```java
Object - null
Byte,short,int,long - 0
float,double - 0.0
boolean - false
char - ‘\u 0000’
```

Object reference variable
---

```java
public class Book {
	private String title;
	public String getTitle(){ reference title;}
	public static void main(String [] args){
        Book b = new Book();
        System.out.println(b.getTitle()); 
        String s = b.getTitle(); //compiles and run
        String t = s.toLowercase(); // Runtime Exception
    }
}
```

Array Instance Variable
----
- Array is an object
- Default value null - uninitialized
- Initialized array elements - get default values

Local primitives and Object
----
- Local variable including primitives always ,always,always,always must be initialized before you attempt to use them(not necessarily same line of code)
- Java doesn’t give local variable a default value

```java
class TimeTable  {
	public static  void main(String [] args){
	int x;
	if(args[0] != null){
	    X = 7;
    }
    int y = x; //the compiler will choke here
    }
}
```
- Variable x might not have been initialized

Local Reference Variable
---
- Locally declared references can’t get away with checking for null before use; unless you explicitly initialize the local variable to `null`;

```java
public class TimeTable{
    public static void main(String [] args){
        Date date;
	    if(date ==  null){
	        System.out.println(“date is null”);
        }
    }
}
```
- Compiler error, variable date may not have been initialized
- local reference are not given a default value; in other words, they aren’t `null`.

Local Arrays
---
- Just like any other object references, array references declared within a method must be assigned a value before use.

Assigning one reference variable to another
---
- If we assign an existing instance of an object to a new reference variable, then  two reference variable will hold the same bit pattern. A bit pattern reffering to a specific object on the heap.
- Any time we make any changes at all to a String, the VM will update the reference variable to reference variable to refer to different object.
- What happens when you use a string reference variable to modify a string
    - A new string is created
    - The reference used to modify the string is then assigned the brand new String object

Passing Object reference variable
---
- Reference variable holds bits that represent (to the underlying VM) a way to get to a specific Object in memory.
- Java is actually pass by value for all variable running within a single NM
- It makes no difference if you’re passing primitive or reference variables; you are always passing a copy to the bits in the variable.
- Called method can’t reassign the caller’s original reference variable and make it refer to a different `object` or `null`.

```java
void bar(){
	Foo f = new Foo();
	doStuff(f);
}
void doStuff(Foo f){
	g = setName(“BOO”);
	g = new Foo();
}
```

Reaasign `g` doesn’t reaasign `f`

- When a primitive variable is passed to a method,it is passed by value,which means pass-by-copy-of-the-bits-in-the-variable

The Shadowy world of variables
--
- Shadowing invloves reusing a variable name that’s already been declared somewhere else.

```java
class Foo {
	static int size = 7;
 	Static void changeIt(int size){
	    size = size +200;
	    System.out.println(“”+size); //207
    }

public static void main(String [] args){
	Foo f = new Foo();
	System.out.println(size); //7
	changeIt(size);
	System.out.println(size); //7
    } 
}
```
- Hiding a static variable by shadowing it with local variable

Garbage Collection
---
- The downside of automatic garbage collection is that you can’t completely control when it runs and when it doesn’t.

When does the garbage collector run?
---
- JVM decides when to run the garbage collector

How does GC work?
---
- An object is eligible for garbage collection when no live thread can access it.
- Garbage collection can not ensure that there is enough memory,only that the memory that is available will be managed as efficiently as possible

Writing code that explicitly makes objects eligible for collection

Nulling a reference 
--
```java
public class GrabageTruck {
    public static void main(String [] args){
	    StringBuffer sb = new StringBuffer(“Hello”);
	    System.out.println(sb);
	    Sb = null;// now Stringbuffer is eligible for garbage collection
    }
}
```
Reassigning a reference variable
---

```java
Class GarbageTruck{
    public static void main(String [] args){
        StringBuffer s1 = new StringBuffer(“hello”);
    	StringBuffer s2 = new StringBuffer(“garbage”);
  		System.out.println(s1);
          S1 = s2; //redirects s1 and s2 refer to the goodbye object
          // now Stringbuffer “Hello” is eligible for collection
     }
}

```
- If an object is returned from a method,its reference might be assigned to a reference variable in the method that called it.hence it will not be eligible for collection.


















