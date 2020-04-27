# Concurrency

## Using Concurrent Collections

### Introducing Concurrent Collections
* using the concurrent collections is extremely convenient in practice.
* It also prevents us from introducing mistakes in own custom implementation, such as if we forgot to synchronize one of the accessor methods. 
* In fact, the concurrent collections often include performance enhancements that avoid unnecessary synchronization.

```java
private Map<String,Object> foodData = new ConcurrentHashMap<String,Object>();
```
### Understanding Memory Consistency Errors
### Working with Concurrent Classes
### Obtaining Synchronized Collections

## Working with Parallel Streams

A _parallel stream_ is a stream that is capable of processing results concurrently, using multiple threads.

### Creating Parallel Streams

#### `parallel()`

```java
Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6).stream(); 
Stream<Integer> parallelStream = stream.parallel();
```

#### `parallelStream()`

```java
Stream<Integer> parallelStream2 = Arrays.asList(1,2,3,4,5,6).parallelStream();
```

### Processing Tasks in Parallel

```java
Arrays.asList(1,2,3,4,5,6)
      .parallelStream()
      .forEach(s -> System.out.print(s+" "));
```
-  the results are no longer ordered or predictable.
- `forEachOrdered()`, which forces a parallel stream to process the results in order at the cost of performance
 
### Understanding Performance Improvements
- a _parallel stream_ can have a four-fold improvement in the results. Even better, the results scale with the number of processors. _Scaling_ is the property that, as we add more resources such as CPUs, the results gradually improve.

-  _Parallel streams_ tend to achieve the most improvement when the number of elements in the stream is significantly large

### Understanding Independent Operations
_Independent operations_, we mean that the results of an operation on one element of a stream do not require or impact the results of another element of the stream.

```java
Arrays.asList("jackal","kangaroo","lemur") 
      .parallelStream()
      .map(s -> s.toUpperCase()) 
      .forEach(System.out::println);
```

### Avoiding Stateful Operations
* _stateful lambda expression_ is one whose result depends on any state that might change during the execution of a pipeline.
* It strongly recommended that you avoid stateful operations when using parallel streams, so as to remove any potential data side effect

### Processing Parallel Reductions

#### Performing Order-Based Tasks

```java
System.out.print(Arrays.asList(1,2,3,4,5,6).parallelStream().findAny().get());
```
The result is that the output could be 4, 1, or really any value in the stream.

- Any stream operation that is based on order, including `findFirst()`, `limit()`, or `skip()`, may actually perform more slowly in a parallel environment.

- using an unordered version has no effect, but on parallel streams, the results can greatly improve performance:

```java
Arrays.asList(1,2,3,4,5,6).stream().unordered().parallel();
```
#### Combining Results with `reduce()`

Requirements for `reduce()` Arguments:
* The _identity_ must be defined such that for all elements in the `stream u, combiner.apply(identity, u)` is equal to u.
* The _accumulator_ operator op must be associative and stateless such that `(a op b) op c is equal to a op (b op c)`.
* The _combiner_ operator must also be associative and stateless and compatible with the identity, such that for all u and t `combiner.apply(u,accumulator.apply(identity,t))` is equal to `accumulator.apply(u,t)`.

### Combing Results with `collect()`

```java
Stream<String> stream = Stream.of("w", "o", "l", "f").parallel();
SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new, Set::add, Set::addAll);
System.out.println(set); // [f, l, o, w]
```
 -  `ConcurrentSkipListSet` are sorted according to their natural ordering.

#### Using the One-Argument collect() Method

```java
Stream<String> stream = Stream.of("w", "o", "l", "f").parallel(); 
Set<String> set = stream.collect(Collectors.toSet());
System.out.println(set); // [f, w, l, o]
```
#### Requirements for Parallel Reduction with `collect()`
* The stream is parallel.
* The parameter of the collect operation has the `Collector.Characteristics.CONCURRENT characteristic`.
* Either the stream is unordered, or the collector has the characteristic `Collector.Characteristics.UNORDERED`.

The `Collectors` class includes two sets of methods for retrieving collectors
that are both `UNORDERED` and `CONCURRENT`, `Collectors.toConcurrentMap()` and `Collectors.groupingByConcurrent()`, and therefore it is capable of performing parallel reductions efficiently. 

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel(); ConcurrentMap<Integer, String> map = ohMy.collect(Collectors.toConcurrentMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
System.out.println(map); // {5=lions,bears, 6=tigers}
System.out.println(map.getClass()); // java.util.concurrent.ConcurrentHashMap
```

`groupingBy()` example to use a parallel stream and parallel reduction:

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel(); ConcurrentMap<Integer, List<String>> map = ohMy.collect(
Collectors.groupingByConcurrent(String::length)); System.out.println(map); // {5=[lions, bears], 6=[tigers]}
```

    
