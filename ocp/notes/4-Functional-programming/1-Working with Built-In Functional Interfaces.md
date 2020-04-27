# Built-In Functional Interfaces

| Functional Interfaces  | # Parameters    | Return Type    |Single Abstract Method   |
|------------------------|-----------------|----------------|-------------------------|
|  `Supplier<T>`         | `0`             |     `T`        |    `get`                |   
|  `Consumer<T>`         | `1(T)`          |   `void`       |    `accept`             |   
|  `BiConsumer<T, U>`    | `2(T,U)`        |   `void`       |    `accept`             |   
|  `Predicate<T>`        | `1(T)`          |  `boolean`     |    `test`               |   
|  `BiPredicate<T, U>`   | `2(T,U)`        |  `boolean`     |    `test`               |   
|  `Function<T, R>`      | `1(T)`          |    `R`         |    `apply`              |   
|  `BiFunction<T, U, R>` | `2(T,U)`        |    `R`         |    `apply`              |   
|  `UnaryOperator<T>`    | `1(T)`          |    `T`         |    `apply`              |   
|  `BinaryOperator<T>`   | `2(T,T)`        |    `T`         |    `apply`              |   

## Implementing Supplier

A Supplier is used when you want to generate or supply values without taking any input.

```java
@FunctionalInterface 
public class Supplier<T> { 
    public T get();
}
```

```java
Supplier<StringBuilder> s1 = StringBuilder::new;
Supplier<StringBuilder> s2 = () -> new StringBuilder();
System.out.println(s1.get());  
System.out.println(s2.get());
```

## Implementing Consumer and BiConsumer
You use a Consumer when you want to do something with a parameter but not return anything.
```java
@FunctionalInterface 
public class Consumer<T> { 
    void accept(T t);
}
```
```java
@FunctionalInterface 
public class BiConsumer<T, U> {
    void accept(T t, U u);
}
```

```java 
Consumer<String> c1 = System.out::println; 
Consumer<String> c2 = x -> System.out.println(x);
```

## Implementing Predicate and BiPredicate

```java
@FunctionalInterface
public class Predicate<T> { 
    boolean test(T t);
}

@FunctionalInterface 
public class BiPredicate<T, U> {
    boolean test(T t, U u); 
}
```

### Example:

```java
Predicate<String> p1 = String::isEmpty;
Predicate<String> p2 = x -> x.isEmpty();
```
## BiPredicate:

```java
BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
```

- the first parameter in the lambda is used as the instance on which to call the method

## Implementing Function and BiFunction
A `Function` is responsible for turning one parameter into a value of a potentially different type and returning it. 

Similarly,a `BiFunction` is responsible for turning two parameters into a value and returning it.

```java
@FunctionalInterface 
public class Function<T, R> { 
    R apply(T t);
}
```

```java
@FunctionalInterface 
public class BiFunction<T, U, R> { 
    R apply(T t, U u);
}
```

For example, this function converts a String to the length of the String:

```java
Function<String, Integer> f1 = String::length; 
Function<String, Integer> f2 = x -> x.length();
```

## Implementing UnaryOperator and BinaryOperator
`UnaryOperator` and `BinaryOperator` are a special case of a function. They require all type parameters to be the same type.

```java
@FunctionalInterface 
public class UnaryOperator<T> extends Function<T, T> { }

@FunctionalInterface 
public class BinaryOperator<T> extends BiFunction<T, T, T> { }
```
This means that method signatures look like this:

```java
T apply(T t);
T apply(T t1, T t2);
```

```java
UnaryOperator<String> u1 = String::toUpperCase; 
UnaryOperator<String> u2 = x -> x.toUpperCase();
System.out.println(u1.apply("chirp"));
System.out.println(u2.apply("chirp"));
```

## Checking Functional Interfaces
- a Predicate returns a `boolean` primitive and not a `Boolean` object

