# Working with Primitives

`IntStream`: Used for the primitive types `int`, `short`, `byte`, and `char`

`LongStream`: Used for the primitive type `long`

`DoubleStream`: Used for the primitive types `double` and `float`

```java
DoubleStream empty = DoubleStream.empty();
DoubleStream oneValue = DoubleStream.of(3.14);
DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
DoubleStream random = DoubleStream.generate(Math::random); 
DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
```

```java
IntStream range = IntStream.range(1, 6);
IntStream rangeClosed = IntStream.rangeClosed(1, 5);
```
-  The `range()` method indicates that we want the numbers 1–6, not including the number 6
- `rangeClosed()` an inclusive range


![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-01-31%20at%2010.08.21%20PM.png )

```java
Stream<String> objStream = Stream.of("penguin", "fish"); 
IntStream intStream = objStream.mapToInt(s -> s.length())
```
![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-01-31%20at%2010.08.33%20PM.png)

### Using Optional with Primitive Streams

```java
IntStream stream = IntStream.rangeClosed(1,10); 
OptionalDouble optional = stream.average();
```
- The difference is that `OptionalDouble` is for a primitive and `Optional<Double>` is for the `Double` wrapper class.
- The `sum()` method does not return an optional. If you try to add up an empty stream, you simply get zero. 

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-12%20at%2010.01.38%20PM.png)

### Summarizing Statistics

`Statistic` is just a big word for a number that was calculated from data.

```java
private static int range(IntStream ints) { 
    IntSummaryStatistics stats = ints.summaryStatistics(); 
    if (stats.getCount() == 0) throw new RuntimeException(); 
    return stats.getMax()—stats.getMin();
}
```

### Learning the Functional Interfaces for Primitives

#### Functional Interfaces for boolean

```java
BooleanSupplier b1 = () -> true;
System.out.println(b1.getAsBoolean());
```

# Functional Interfaces for `double`, `int`, and `long`

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-01-31%20at%2010.54.51%20PM.png)

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-01-31%20at%2010.54.59%20PM.png)

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-01-31%20at%2010.55.06%20PM.png)

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-01-31%20at%2010.55.13%20PM.png)

