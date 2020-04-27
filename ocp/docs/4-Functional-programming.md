Functional Programming
---




```

To Remember
-----------

* Streams can be used only once, this throws an exception that the _“stream has already been operated upon or closed.”_

```java
Stream<String> stream = Stream.iterate("-", (s) -> s + s); 
boolean b1 = stream.noneMatch(predicate);
boolean b2 = stream.anyMatch(predicate);
```
* The `partitioningBy()` operation always returns a map with two Boolean keys, even if there are no corresponding values. By contrast, `groupingBy()` returns only keys that are actually needed.

```java
 Stream<String> s = Stream.empty();
 Stream<String> s2 = Stream.empty();
 Map<Boolean, List<String>> c = s.collect(Collectors.partitioningBy(b -> b.startsWith("c")));
 Map<Boolean, List<String>> c2 = s2.collect(Collectors.partitioningBy(b -> b.startsWith("c")));
 System.out.println(c+" "+c2);
```

* Lambdas for `Supplier<Double>` can return a `null` value since `Double` is an object type, while lambdas for `DoubleSupplier` cannot; they can only return primitive `double` values.
* `CharSupplier` does not exist in `java.util.function`.
* the `forEach()` method requires a `Consumer` instance and `DoubleConsumer` does not inherit from `Consumer`.
* `intValue()` can be called on a `Long` object. The result can then be cast to `long`, which is autoboxed to `Long`.
* The `Supplier` functional interface normally takes a generic argument, although generic types are not strictly required since they are removed by the compiler.
* A lambda may be compatible with multiple functional interfaces, but it must be assigned to a functional interface when it is declared or passed as a method argument. 
* The `return` statement is only allowed inside a set of brackets `{}`.
* Java only includes primitive functional interfaces that operate on `double`, `int`, or `long`.
* `BooleanSupplier` is the only functional interface that does not involve `double`, `int`, or `long`.
* 
