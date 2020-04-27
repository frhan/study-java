# Working with Parallel Streams

## Creating Parallel Streams

### `parallel()`

```java 
Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6).stream(); 
Stream<Integer> parallelStream = stream.parallel();
```

### `parallelStream()`

`Stream<Integer> parallelStream2 = Arrays.asList(1,2,3,4,5,6).parallelStream();`


## Processing Tasks in Parallel

```java
Arrays.asList(1,2,3,4,5,6)
.parallelStream()
.forEach(s -> System.out.print(s+" "));
```

## Understanding Performance Improvements

Parallel streams tend to achieve the most improvement when the number of elements in the stream is significantly large. 
For small streams, the improvement is often limited, as there are some overhead costs to allocating and setting up the parallel processing.

### Understanding Independent Operations

By independent operations, we mean that the results of an operation on one element of a stream do not require or impact the results of another element of the stream.

```java
Arrays.asList("jackal","kangaroo","lemur") .parallelStream()
.map(s -> s.toUpperCase()) .forEach(System.out::println);
```
In this example, mapping jackal to JACKAL can be done independently of mapping kangaroo to KANGAROO. In other words, multiple elements of the stream can be processed at the same time and the results will not change.

### Avoiding Stateful Operations
avoid stateful operations when using parallel streams, so as to remove any potential data side effects. In fact, they should generally be avoided in serial streams wherever possible, since they prevent your streams from taking advantage of parallelization.

## Processing Parallel Reductions
Any stream operation that is based on order, including `findFirst()` , `limit()` , or
`skip()` , may actually perform more slowly in a parallel environment. This is a result of a
parallel processing task being forced to coordinate all of its threads in a synchronized-like
fashion.


