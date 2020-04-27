# Working with Advanced Stream Pipeline Concepts

### Linking Streams to the Underlying Data

```java
25: List<String> cats = new ArrayList<>();
26: cats.add("Annie");
27: cats.add("Ripley");
28: Stream<String> stream = cats.stream();
29: cats.add("KC");
30: System.out.println(stream.count());
```
- streams are lazily evaluated. This means that the stream isn’t actually created on line 28.
-  On line 29, the List gets a new element. On line 30, the stream pipeline actually runs.

### Chaining Optionals

```java
private static void threeDigit(Optional<Integer> optional) {
    optional.map(n -> "" + n)
            .filter(s -> s.length() == 3) 
            .ifPresent(System.out::println);
}
```

the signature `static Optional<Integer> calculator(String s)`? Using `map` doesn’t work:

```java
Optional<Integer> result = optional.map(ChainingOptionals::calculator); // DOES NOT COMPILE
```

- The problem is that `calculator` returns `Optional<Integer>`. The `map()` method adds another  `Optional`, giving us `Optional<Optional<Integer>>`

The solution is to call `flatMap()` instead:

```java
Optional<Integer> result = optional.flatMap(ChainingOptionals::calculator);
```

#### Checked exceptions and Functional interfaces


Collecting Results
-------------

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-01-31%20at%2011.27.29%20PM.png)

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-01-31%20at%2011.27.36%20PM.png)

Collecting Using Basic Collectors
----------

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
String result = ohMy.collect(Collectors.joining(", ")); 
System.out.println(result); // lions, tigers, bears
```

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Double result = ohMy.collect(Collectors.averagingInt(String::length));
System.out.println(result); // 5.333333333333333
```