Flow control and Exceptions
-----

Flow Control
----
if statement
---
```java
if(boolean expression){  // must true or false
 //statement
}
```
- else block optional
- curly braces are optional
- else clause belongs to the innermost if statement to which it might possibly belong.
- the closest preceding if that doesn't have an else.

```java
int trueInt = 1;
if(trueInt) //illegal
boolean boo = false;
if(boo = true)
{
}
```
- the code compiles and runs fine and the if test succeeds because boo is set to true in the if argument.

```java
int x = 3;
if(x = 5) //won't compile; because x is not a boolean
{
}
```

Switch statement
---
- break statement are optional

```java
switch(expression){
    case constant1: //code block
    case constant2: //code block
    default : //code block
}
```
expression - `char,byte,short,int ,enum,String`
 - case constant must be a compile time constant
 - we can use only final variable that is immediately initialized with a literal value. 

```java
final int a = 1;
final int b;

b = 2;
int x = 0;
switch(x){
    case a:  //OK
    case b:  //compiler error
}

byte g = 2;
switch(g){
    case 23:
    case 128: //this code won't compile
}
```
- also illegal to have more than one case label using the same value
- legal to leverage the power of boxing in a switch expression.

```java
switch(new Integer(4)){
     case 4:  //legal
  }
```

String "equality" in switch statement
---
- two strings will be considered "equal" if they have the same case-sensitive sequence of character.
  ```java
  String s = "Monday";
    switch(s){
      	case "Monday": //Matches!
 	}
   ```
 - the first case constant that matches the switch's expression is the execution entry point "Fall through".

- `default` case doesn't have to come at the end of the switch.
    ```java
        int x =7;
        switch(s){
  	        case 2: 
 	        default: System.out.println("Default");
 	        case 3: System.out.println("3");
 	        case 4: System.out.println("4");
        }
    ```
Output:
 Default
 3
 4
- `default` works just like any other case for fall through.

Creating Loop Constant
---

While loop
---

```
while(expression){
}
```
- any variable used in  the expression of while loop must be declared before the expression is evaluated.

```java
while(int x = 2){} //not legal
int x =2;
while(x = 3){} //not legal
```
- the while loop might not even run,if the test expression is false the while expression is checked.

Do Loop
---

same as while loop except 

- expression is not evaluated until after the do loop's code is executed.

```java
do{
//inside
}while(false);
```
- do loop always run the code in the loop body at least once.

Using for loop
----

basic for loop
```java
for(/*initialization*/;/*condition*/;/*Iteration*/){
    //body
}

for(int x = 0; ((x<10) && (y-- > 2)) ; x++){ // legal
}

for(int x = 0; (x>5),(y< 2); x++){ //not legal
//too many expression
}
```
- you can only have one test expression
- other two parts of a for statement can have multiple parts

```java
for(int i =0; i<10 ; i++){
    break; // won't compile; unreachable code
    i++;
} 
```

loop to terminate abruptly
---
a break,a return, a `sytem.exit()`

`break` - execution jumps immediately to the first statement after for loop.
`return` - execution jump immediately back to the calling method
`System.exit()` - All program execution stops, the VM shuts down.

- with absence of initialization and increment section - for loop act like while loop
- all three section of the for loop are independent  of each other
- they don't need to operate on the same variable
- in iteration section
- put in virtually any arbitrary code statement you want

```java
for( int i =0; i< 3 ; System.out.println("Y")){
 	i--; //legal
} 
```
Enhanced for loop
---

- looping through an array or collection

```java
int [] a = {1,2,3,4,5};
for(int i : a){
    //body
} 
```

two pieces of the for statement
---

`for(declaration:expression)`

`declaration` - block variable, of a type compatible with the elements of the array you are accessing
`expression` - array variable or method call that returns an array

arrays can be  - type of
---
- primitives 
-  objects  or even arrays of array

`continue` - statement must be inside a loop
`break` - inside either a loop or switch statement
`continue`  - causes only the current iteration of the innermost loop to cease
 	 	    - don't worry about what was below the continue statement

Labeled Statement
---
- The labeled varieties are needed only in situations where you have a nested loop, and they need to indicate which of the nested loops you want to break from, or from which of the nested loops you want to continue with the next iteration. 
- placed before the statement being labeled 
- valid identifier that ends with a colon(:)
- must be valid variable name

```java
foo:
for(...){}
```

Handling Exception
---

- catch block immediately follow the try block
- orders in which the catch block appears matters
- if an exception occurs in try block,the rest of the line would never be executed

Finally
---
- encloses code block that is always executed at some point after the try block.whenever an exception was thrown or not
- even if there is a return statement in the try block, the finally block executes right after the return statement is encountered and before the return executes
- if there was an exception, the finally block executes immediately after the proper catch block
- finally always runs

```java
try{
// do stuff
}finally{
//clean up
}

try{
//illegal
}

try{
}
System.out.println("XYZ"); //illegal
catch(Exception e){
}
```

- any catch clauses must immediately follow the try block
- any finally clause must immediately follow the last catch clause - or it must immediately follow the try block
- its legal to omit the catch clause or finally clause but not both
- an exception that's never caught will cause your application to stop running
- the handlers for the most specific exception must always be placed above those for more general exception
    - this is really important
    - if we do it the opposite way, the program will not compile
- the exception that a method can through must be declared (unless the exception are subclasses of `RuntimeException`)
- methods that aren't actually throwing exception directly, but are 'ducking' and letting the exception pass down to the next method in the stock
- Each method must either handle all checked exception by supplying a catch clause or list each unhandled checked exception a thrown exception
     		     
                  "Handle or Declare"
- if a method does declare a RuntimeException, the calling method is under no obligation to handle or declare it
- RuntimeException,Error and all of their subtypes are unchecked exception and unchecked exception do not have to be specified or handle.

Rethrowing the same exception
---

```java
void method() throws IOException{
 	try{
 	}
 	catch(IOException e){
    	throw e;
 	}
 }
```
- must declare the exception on method

```java
void go(){
    go(); //StackOverflowError
}
```

Extra
---

the exceptions are `RuntimeExceptions` so there is no need to catch these. But it is ok even if the method declares them explicitly

```java
public class TestClass{
public static void main(String args[]){
try{
   RuntimeException re = null;
   throw re;
}catch(Exception e){
   System.out.println(e);
}
}
}
```
-- The program will compile without error and will print `java.lang.NullPointerException` when run

Exception Handling
---------------------------
You can put a Super class in the throws clause and then you can throw any subclass exception.
A subclass of Error cannot be caught using a catch block for Exception because `java.lang.Error` does not extend `java.lang.Exception`.
`int i = Integer.parseInt(s); throws a NumberFormatException` because 12.3 is not an integer
The `main(String[] args)` method is the last point in your program where any unhandled checked exception can bubble up to.
After that the `exception` is thrown to the JVM and the JVM kills the thread.

When you use `System.out.println(exception)`, a stack trace is not printed. Just the name of the exception class and the message is printed. 

When you use `exception.printStackTrace()`, a complete chain of the names of the methods called, along with the line numbers, is printed from the point where the exception was thrown and up to the point where the exception was caught and `printStackTrace()` was called.

You can declare anything that is a `Throwable` or a subclass of `Throwable`, in the
throws clause.

Fundamental concept:
The `Exception` that is thrown in the last, is the `Exception` that is thrown by the
method to the caller

`Exception` thrown by a catch cannot be caught by the following catch blocks at the same level.

The `catch` argument type declares the type of exception that the handler can
handle and must be the name of a class that extends Throwable or Throwable
Itself.

Compilation Error
-------
```java
class TestClass {
String m1(int[] i) { return ""+i.length; }
String m1(int... i) { return ""+i.length; } // Compiler determines that both array
}
```
