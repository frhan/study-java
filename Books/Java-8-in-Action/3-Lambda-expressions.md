Lambda expressions
---

* lambda expressions, which let you represent a behavior or pass code in a concise way.

**Lambdas in a nutshell**

* Anonymous
* Function
* Passed around
* Concise

it originates from a system developed in academia called _lambda calculus_, which is used to describe computations.

Lambdas technically don’t let you do anything that you couldn’t do prior to Java 8.

**The lambda we just showed you has three parts**

* A list of parameters
* An arrow
* The body of the lambda

The basic syntax of a **lambda** is either
```
(parameters) -> expression
```
or (note the curly braces for statements)
```
(parameters) -> { statements; }
```

**Where and how to use lambdas**

* You can use a lambda expression in the con- text of a functional interface.

Functional interface
---
a _functional interface_ is an interface that specifies exactly one abstract method

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}

public interface Runnable{
    void run();
}
```

Function descriptor
---
* The signature of the abstract method of the functional interface essentially describes the signature of the lambda expression

* The notation ```() -> void``` represents a function with an empty list of parameters returning ```void```.

* ```(Apple, Apple) -> int``` denotes a function taking two Apples as parameters and returning an ```int```.

* a lambda expression can be assigned to a variable or passed to a method expecting a functional interface as argument, provided the lambda expression has the same signature as the abstract method of the functional interface.

* functional interfaces are annotated with ```@FunctionalInterface```


Four-step process to apply the execute around pattern
---
step 1:

```java
public static String processFile() throws IOException { 
    try (BufferedReader br =
                    new BufferedReader(new FileReader("data.txt"))){
        return br.readLine();
    }
}
```
step 2:

```java 
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
public static String processFile(BufferedReaderProcessor p) throws IOException {
... 
}
```
step 3:

```java
public static String processFile(BufferedReaderProcessor p) throws IOException {
   try (BufferedReader br =
            new BufferedReader(new FileReader("data.txt"))){
       return p.process(br);
   }
}
```

step 4:

```java 
String oneLine = processFile((BufferedReader br) -> br.readLine());
    
String twoLines = processFile((BufferedReader br) -> br.readLine()) + br.readLine());
```

* The signature of the abstract method of a functional interface is called a _function descriptor_.

Predicate
---

* The ```java.util.function.Predicate<T>``` interface defines an abstract method named ```test``` that accepts an object of generic type ```T``` and returns a ```boolean```.

* need to represent a boolean expression that uses an object of type **T**.

```java
@FunctionalInterface
interface Predicate<T>{
  boolean test(T t);
}

public <T> List<T> filter(List<T> list, Predicate<T> predicate){
    List<T> newList = new ArrayList<T>();

    for (T t: list){
      if (predicate.test(t)){
        newList.add(t);
      }
    }
    return newList;
  }

Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
```

Consumer
----
The ```java.util.function.Consumer<T>``` interface defines an abstract method named accept that takes an object of generic type **T** and returns no result (void).

```java 
@FunctionalInterface
public interface Consumer<T>{
    void accept(T t);
}
public static <T> void forEach(List<T> list, Consumer<T> c){
    for(T i: list){
        c.accept(i);
    }
}

forEach( Arrays.asList(1,2,3,4,5),
                (Integer i) -> System.out.println(i)
);
```

Function
---

The ```java.util.function.Function<T, R>``` interface defines an abstract method named apply that takes an object of generic type ```T``` as input and returns an object of generic type ```R```.

 create a method map to transform a list of **Strings** into a list of **Integers* containing the length of each **String**.

 ```java 
@FunctionalInterface
interface Function<T, R> {
  R apply(T t);
}
  
static <T, R> List<R> map(List<T> list, Function<T, R> f) {

    List<R> newList = new ArrayList<>();

    for (T t : list) {
      newList.add(f.apply(t));
    }
    return newList;
}

// [7, 2, 6]
  List<Integer> l = map(Arrays.asList("lambdas","in","action"),(String s) -> s.length());
```

Java 8 brings a specialized version of the functional interfaces, in order to avoid autoboxing operations when the inputs or outputs are primitives.

```java 
public interface IntPredicate{
    boolean test(int t);
}
IntPredicate evenNumbers = (int i) -> i % 2 == 0;
evenNumbers.test(1000);
```

What about exceptions, lambdas, and functional interfaces?
---

* if you need a lambda expression to throw an exception: define your own functional interface that declares the checked exception, or wrap the lambda with a try/catch block

```java 
Function<BufferedReader, String> f =
  (BufferedReader b) -> {
    try {
      return b.readLine();
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }
};
```
 
 * The type expected for the lambda expression inside the context (for example, a method parameter that it’s passed to or a local variable that it’s assigned to) is called the target type.

Using local variables
-----

 * lambda expressions are also allowed to use free variables (variables that aren’t the parameters and defined in an outer scope) just like anonymous classes can.

 ```java 
 int portNumber = 1337;
 Runnable r = () -> System.out.println(portNumber);
 ```

* Lambdas are allowed to capture (that is, to reference in their bodies) instance variables and static variables without restrictions. But local variables have to be explicitly declared final or are effectively final.


Method references
---

* Method references can be seen as short- hand for lambdas calling only a specific method. 

```java
inventory.sort((Apple a1, Apple a2)
                -> a1.getWeight().compareTo(a2.getWeight()));
```
After (using a method reference and java.util.Comparator.comparing):

```java
inventory.sort(comparing(Apple::getWeight));
```

* When you need a method reference, the target reference is placed before the delimiter :: and the name of the method is provided after it

RECIPE FOR CONSTRUCTING METHOD REFERENCES
---
1. A method reference to a static method. like ``` Integer::parseInt```
2. A method reference to an instance method of an arbitrary type. ```String::length```
3. A method reference to an instance method of an existing object. ``` expensiveTransaction::getValue```

Constructor references
---

You can create a reference to an existing constructor using its name and the keyword new as follows: ```ClassName::new``` 

```java

Supplier<Apple> c1 = Apple::new;
Apple a1 = c1.get();

```
which is equivalent to

```java

Supplier<Apple> c1 = () -> new Apple();
Apple a1 = c1.get();

```

```java 

static Map<String,Function<Integer,Fruit>> map = new HashMap<>();

  static {
    map.put("apple",Apple::new);
    map.put("apple",Orange::new);
  }


static Fruit giveMeFruit(String name){
    return map.get(name.toLowerCase())
            .apply(10);
  }
```

Putting lambdas and method references into practice!
----

Step 1: Pass code

```java 
public class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2){
                return a1.getWeight().compareTo(a2.getWeight());
}
}
inventory.sort(new AppleComparator());
```

Step 2: Use an anonymous class

```java 
inventory.sort(new Comparator<Apple>() {
    public int compare(Apple a1, Apple a2){
        return a1.getWeight().compareTo(a2.getWeight());
    }
});
```

Step 3: Use lambda expressions

```java
inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
```

Step 4: Use method references

```java 
inventory.sort(comparing(Apple::getWeight));
```

Composing Comparators
----

REVERSED ORDER
------

```
inventory.sort(comparing(Apple::getWeight).reversed());
```

CHAINING COMPARATORS
----

```java 
inventory.sort(comparing(Apple::getWeight)
         .reversed()
         .thenComparing(Apple::getCountry));

```

Composing Predicates
---

```java

Predicate<Apple> redAndHeavyAppleOrGreen =
    redApple.and(a -> a.getWeight() > 150)
            .or(a -> "green".equals(a.getColor()));

```

Composing Functions
----

```andThen```

```java
Function<Integer, Integer> f = x -> x + 1;
Function<Integer, Integer> g = x -> x * 2;
Function<Integer, Integer> h = f.andThen(g);
int result = h.apply(1);
```
f(x)
g(f(x)) or (g o f)

```compose```

```java
Function<Integer, Integer> f = x -> x + 1;
Function<Integer, Integer> g = x -> x * 2;
Function<Integer, Integer> h = f.compose(g);
int result = h.apply(1);
```
f(x)
f(g(x)) or (f o g)