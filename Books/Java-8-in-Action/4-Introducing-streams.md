Introducing streams
===

* Nearly every Java application _makes_ and _processes_ collections.

What are streams?
---

* _Streams_ are an update to the Java API that lets you manipulate collections of data in a declarative way

* _streams_ can be processed in parallel transparently, without you having to write any multithreaded code!

 the names of dishes that are low in calories, Java 7:

```java 
List<Dish> lowCaloricDishes = new ArrayList<>();
for(Dish d: menu){
    if(d.getCalories() < 400){
        lowCaloricDishes.add(d);
    }
}

Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
    public int compare(Dish d1, Dish d2){
        return Integer.compare(d1.getCalories(), d2.getCalories());
        }
});

List<String> lowCaloricDishesName = new ArrayList<>();
for(Dish d: lowCaloricDishes){
    lowCaloricDishesName.add(d.getName());
}

```
After (Java 8):

```java
List<String> lowCaloricDishesName = menu.stream()
                                        .filter(d -> d.getCalories() <400)
                                        .sorted(sorting(Dish::getCalories))
                                        .map(Dish::getName())
                                        .collect(toList());
```

Parallel:


```java
List<String> lowCaloricDishesName = menu.parallelStream()
                                        .filter(d -> d.getCalories() <400)
                                        .sorted(sorting(Dish::getCalories))
                                        .map(Dish::getName())
                                        .collect(toList());
```

benefits from a software engineering point of view:

* The code is written in a _declarative_ way
* chain together several building-block operations to express a complicated data processing pipeline while keeping your code readable and its intent clear

what exactly is a stream?
---

*  _Sequence_ of elements : Collections are about data; streams are about computa- tions.
* _Source_ 
* _Data processing operations_

stream operations have two important characteristics:

* Pipelining
* Internal iteration

`filter` : Takes a lambda to exclude certain elements from the stream.

`map` : Takes a lambda to transform an element into another one or to extract information

`limit` : Truncates a stream to contain no more than a given number of elements.

`collect` : Converts a stream into another form

Streams vs. collections
---

*  the difference between collections and streams has to do with when things are computed
* A stream is a conceptually fixed data structure (you can’t add or remove elements from it) whose elements are computed on demand

Traversable only once
----
 
 *  a stream can be traversed only once.

 ```java 
 List<String> title = Arrays.asList("Java8", "In", "Action");
Stream<String> s = title.stream();
s.forEach(System.out::println); 
s.forEach(System.out::println); //java.lang.IllegalStateException: stream has already been operated                                      //upon or closed.
 ```

External vs. internal iteration
---

Collections: external iteration with a for-each loop :

```java 
List<String> names = new ArrayList<>();
for(Dish d: menu){
    names.add(d.getName());
}
```

Streams: internal iteration:

```java
List<String> names = menu.stream()
                         .map(Dish::getName)
                         .collect(toList());
```


Stream operations
----

* Stream operations that can be connected are called _intermediate operations_
* operations that close a stream are called _terminal operations_

menu -> `filter` -> `map` -> `limit` -> `collect`

Intermediate operations
---

* Intermediate operations such as `filter` or `sorted` return another stream as the return type.

* intermediate operations don’t perform any processing until a termi- nal operation is invoked on the stream pipeline—they’re lazy.

Terminal operations
-----

* Terminal operations produce a result from a stream pipeline.

* `forEach` is a terminal operation that returns void and applies a lambda to each dish in the source.

```java 
menu.stream().forEach(System.out::println);
```

Working with streams
---
To summarize, working with streams in general involves three items:

* A data source (such as a collection) to perform a query on
* A chain of intermediate operations that form a stream pipeline
* A terminal operation that executes the stream pipeline and produces a result


Intermediate operations
---
