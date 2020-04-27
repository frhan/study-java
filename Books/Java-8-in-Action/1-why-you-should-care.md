Java 8 : why should you care
---

* Java 8 you can write more concise code that reads a lot closer to the problem statement

``` java
  inventory.sort(comparing(Apple::getWeight));
```

*  Java 8 has a new, simpler way of thinking about parallelism.

* The Java 8 feature of passing code to methods (and also being able to return it and incorporate it into data structures) also provides access to a whole range of additional techniques that are commonly referred to as functional-style programming.

**Stream processing**

a stream is a sequence of data items that are conceptually produced one at a time a program might read items from an input stream one by one and similarly write items to an output stream.

The Unix pipes (|)
```
  cat file1 file2  |  tr "[A-Z]"  "[a-z]"  |  sort  |  tail -3
```

Java 8 adds a **Streams API** (note the uppercase S) in `java.util.stream` based on this idea; Stream<T> is a sequence of items of type T.

* Java 8 can transparently run your pipeline of Stream operations on several CPU cores on disjoint parts of the input—this is parallel- ism almost for free instead of hard work using Threads.

**Passing code to methods with behavior parameterization**

 * the ability to pass a piece of code to an API
 * this conceptually as _behavior parameterization_.

**Parallelism and shared mutable data**

*  writing code that doesn’t access shared mutable data to do its job
* referred to as pure functions or side-effect-free functions or stateless functions

**Java needs to evolve**

* the functional-style programming spectrum in which what you want to do in broad-brush terms is considered prime and separated from how you can achieve this.

**Functions in Java**
 * Values are first-class Java citizens, but various other Java concepts, such as methods and classes, exemplify second-class citizens.

**Methods and lambdas as first-class citizens**

prior java 8 -  filter all the hidden files in a directory.

```java
File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
    public boolean accept(File file) {
        return file.isHidden();
    }
});
```
after java 8 -

```java
  File[] hiddenFiles = new File(".").listFiles(File::isHidden);
```

**LAMBDAS—ANONYMOUS FUNCTIONS**

What’s a Predicate?

The word predicate is often used in mathematics to mean something functionlike that takes a value for an argument and returns true or false.

**From passing methods to lambdas**

```java
 filterApples(inventory, (Apple a) -> "green".equals(a.getColor()) );
```

**Multithreading is difficult**

* exploiting parallelism by writing multithreaded code (using the Threads API from previous versions of Java) is difficult.
* `Streams` is mostly about describing computations on data.
* `Streams` allows and encourages the elements within a Stream to be processed in parallel.

Sequential processing:
```java
  import static java.util.stream.Collectors.toList;
  List<Apple> heavyApples = inventory.stream()
                                      .filter((Apple a) -> a.getWeight() > 150)
                                      .collect(toList());
```

Parallel processing:
```java
  import static java.util.stream.Collectors.toList;
  List<Apple> heavyApples = inventory.parallelStream()
                                    .filter((Apple a) -> a.getWeight() > 150)
                                    .collect(toList());
```

**Parallelism in Java and no shared mutable state**

Java 8:
* First, the library handles partitioning—breaking down a big stream into several smaller streams to be processed in parallel for you.
* Second, this parallelism almost for free from streams works only if the methods passed to library methods like filter don’t interact, for example, by having mutable shared objects

**Default methods**

* an interface can now contain method signatures for which an implementing class doesn’t provide an implementation!



* In Java 8 there’s an `Optional<T>` class that, if used consistently, can help you avoid NullPointer exceptions.

* `Optional<T>` includes methods to explicitly deal with the case where a value is absent, and as a result you can avoid NullPointer exceptions.
