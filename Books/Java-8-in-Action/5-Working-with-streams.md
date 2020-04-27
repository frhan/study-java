Working with streams
====

Filtering and slicing
---

Filtering with a predicate
---

```java
List<Dish> vegetarianMenu = menu.stream()
                                .filter(Dish::isVegetarian)
                                .collect(toList());
```

Filtering unique elements
---

```java
List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
numbers.stream()
       .filter(i -> i % 2 == 0)
       .distinct()
       .forEach(System.out::println);
```

Truncating a stream
---

* Streams support the `limit(n)` method, which returns another stream that’s no longer than a given size.

```java
List<Dish> dishes = menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .limit(3)
                        .collect(toList());
```

Skipping elements
---

* Streams support the `skip(n)` method to return a stream that discards the first n elements.

```java
List<Dish> dishes = menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .skip(2)
                        .collect(toList());
```

Mapping
---

Applying a function to each element of a stream
----

* The function is applied to each element, mapping it into a new element

```java
List<String> dishNames = menu.stream()
                             .map(Dish::getName)
                             .collect(toList());
```

find out the length of the name of each dish:

```java
List<Integer> dishNameLengths = menu.stream()
                                   .map(Dish::getName)
                                   .map(String::length)
                                   .collect(toList());
```

Flattening streams
---

```java
words.stream()
     .map(word -> word.split(""))
     .distinct()
     .collect(toList());
```

* The problem with this approach is that the lambda passed to the `map` method returns a `String[]` (an array of String) for each word.

* What you really want is `Stream<String>` to represent a stream of characters.

ATTEMPT USING MAP AND ARRAYS.STREAM
---

```java
words.stream()
     .map(word -> word.split(""))
     .map(Arrays::stream)
     .distinct()
     .collect(toList());
```

* The current solution still doesn’t work! This is because you now end up with a list of streams (more precisely, `Stream<Stream<String>>`)!

USING FLATMAP
---

```java
List<String> uniqueCharacters = words.stream()
                                    .map(w -> w.split(""))
                                    .flatMap(Arrays::stream) //Flattens each generated stream into a single stream
                                    .distinct()
                                    .collect(Collectors.toList());
```

the `flatMap` method lets you replace each value of a stream with another stream and then concatenates all the generated streams into a single stream.

Finding and matching
---

Checking to see if a predicate matches at least one element
---

```java
if(menu.stream().anyMatch(Dish::isVegetarian)){
    System.out.println("The menu is (somewhat) vegetarian friendly!!");
}
```

checking to see if a predicate matches all elements
---

```java
 boolean isHealthy = menu.stream()
                         .allMatch(d -> d.getCalories() < 1000);
```

NONEMATCH
---

```java
  boolean isHealthy = menu.stream()
                          .noneMatch(d -> d.getCalories() >= 1000);
```

`anyMatch`, `allMatch`, and `noneMatch`, make use of what we call short-circuiting, a stream version of the familiar Java short-circuiting `&&` and `||` operators.

Finding an element
---

The `findAny` method returns an arbitrary element of the current stream.

```java
Optional<Dish> dish = menu.stream()
                          .filter(Dish::isVegetarian)
                          .findAny();
```

OPTIONAL IN A NUTSHELL
---

* The `Optional<T>` class (`java.util.Optional`) is a container class to represent the existence or absence of a value.

* Instead of returning null, which is well known for being error prone, the Java 8 library designers introduced `Optional<T>`.

A few methods available in `Optional`:

* `isPresent()` returns true if Optional contains a value, false otherwise.
* `ifPresent(Consumer<T> block)` executes the given block if a value is present.
* `T get()` returns the value if present; otherwise it throws a `NoSuchElementException`.
* `T orElse(T other)` returns the value if present; otherwise it returns a default value.

```java
menu.stream()
    .filter(Disk::isVegetarian)
    .findAny()
    .ifPresent(d -> System.out.println(d.getName()));
```

Finding the first element
---

```java
List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> firstSquareDivisibleByThree = 
        someNumbers.stream()
                   .map(x -> x * x)
                   .filter(x -> x % 3 == 0)
                   .findFirst();//9
```

When to use `findFirst` and `findAny`
---

* Finding the first element is more constraining in parallel.
* If you don’t care about which element is returned, use `findAny` because it’s less constraining when using parallel streams.

Reducing
---

Summing the elements
---

```java
int [] numbers = {1,2,3,5,6,7,8,9};
int sum = numbers.stream()
                 .reduce(0, (a, b) -> a + b);
```

`reduce` takes two arguments:

* An initial value, here 0.
* A `BinaryOperator<T>` to combine two elements and produce a new value; here you use the `lambda(a,b)->a+b`.

```java
    int sum = numbers.stream().reduce(0, Integer::sum);
```

NO INITIAL VALUE
---

```java 
Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));
```

Maximum and minimum
---

```java
Optional<Integer> max = numbers.stream().reduce(Integer::max);
```

```java
Optional<Integer> min = numbers.stream().reduce(Integer::min);
```

Count
---

```java 
long count = menu.stream().count();
```

Benefit of the reduce method and parallelism
---

```java
int sum = numbers.parallelStream().reduce(0, Integer::sum);
```

Stream operations: stateless vs. stateful
---

* Operations like `map` and `filter` take each element from the input stream and produce zero or one result in the output stream. These operations are thus in general stateless: they don’t have an internal state.

* operations like `reduce`, `sum`, and `max` need to have internal state to accumulate the result. In this case the internal state is small. The internal state is of _bounded size_ no matter how many elements are in the stream being processed.

* Both sorting and removing dupli- cates from a stream require knowing the previous history to do their job


Putting it all into practice
---

The domain: Traders and Transactions
----

```java
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
);
```

Trader class:

```java
  public class Trader{
            private final String name;
            private final String city;
            public Trader(String n, String c){
               this.name = n;
                this.city = c;
            }
            public String getName(){
                return this.name;
            }
            public String getCity(){
                return this.city;
            }
            public String toString(){
                return "Trader:"+this.name + " in " + this.city;
            }
    }
```

Transaction class:

```java
 public class Transaction{
            private final Trader trader;
            private final int year;
            private final int value;
            public Transaction(Trader trader, int year, int value){
                this.trader = trader;
                this.year = year;
                this.value = value;
            }
            public Trader getTrader(){
                return this.trader;
            }

            public int getYear(){
                return this.year;
            }
            public int getValue(){
                return this.value;
            }
            public String toString(){
                return "{" + this.trader + ", " +
                        "year: "+this.year+", " +
                        "value:" + this.value +"}";
            }
}
```

Solutions
---

Find all transactions in 2011 and sort by value (small to high)
---

```java
    List<Transaction> tr2011 = 
            transactions.stream()
                        .filter(t -> t.getYear() == 2011)
                        .sorted(comparing(Transaction::getValue))
                        .collect(toList());

```

What are all the unique cities where the traders work?
---

```java 
    Set<String> cities = transactions.stream()
                                      .map(t -> t.getTrader().getCity())
                                      .collect(toSet());

```

Find all traders from Cambridge and sort them by name
------

```java 

    List<Trader> traders =
            transactions.stream()
                    .map(Transaction::getTrader)
                    .filter(t -> t.getCity().equals("Cambridge"))
                    .distinct()
                    .sorted(comparing(Trader::getName))
                    .collect(toList());
```

Return a string of all traders’ names sorted alphabetically
---

```java
  String traderStr =
            transactions.stream()
                        .map(t -> t.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("",(s1,s2) -> s1+s2);
```

```java 
String traderStr =
    transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
```

Are any traders based in Milan?
---

```java 
    boolean milanBased = 
                transactions.stream()
                            .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

```

Print all transactions’ values from the traders living in Cambridge
---

```java
     transactions.stream()
                  .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                  .map(Transaction::getValue)
                  .forEach(System.out::println);

```

What’s the highest value of all the transactions?
---

```java
 Optional<Integer> highestVal = 
            transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);
```

Find the transaction with the smallest value
---

```java 
    Optional<Transaction> reduce = 
            transactions.stream()
                        .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

```

```java 

Optional<Transaction> smallestTransaction =
  transactions.stream()
              .min(comparing(Transaction::getValue));
```

Numeric streams
---

Primitive stream specializations
---

* Java 8 introduces three primitive specialized stream interfaces
* `IntStream`, `DoubleStream`, and `LongStream`
* avoid hidden boxing costs

MAPPING TO A NUMERIC STREAM
---

`mapToInt` as follows to calculate the sum of calories in the menu:

```java
int calories = menu.stream()
                   .mapToInt(Dish::getCalories)
                   .sum();
```

* if the stream were empty, sum would return 0 by default.

CONVERTING BACK TO A STREAM OF OBJECTS
---

```java
IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
Stream<Integer> stream = intStream.boxed();
```

DEFAULT VALUES: OPTIONALINT
---

```java
OptionalInt maxCalories = menu.stream()
                              .mapToInt(Dish::getCalories)
                              .max();
```

`OptionalInt` explicitly to define a default value if there’s
no maximum:

```java
int max = maxCalories.orElse(1);
```

Numeric ranges
---------------

* Java 8 introduces two static methods available on `IntStream` and `LongStream` to help generate ranges: `range` and `rangeClosed`

* `range` is exclusive, whereas `rangeClosed` is inclusive

A stream of even numbers from 1 to 100: 

 ```java
     IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                                      .filter(n -> n % 2 == 0);
    System.out.println(evenNumbers.count());
 ```

Putting numerical streams into practice: Pythagorean triples 
----

PYTHAGOREAN TRIPLE
------------------

* certain triples of numbers `(a, b, c)` satisfy the formula `a*a+b*b=c*c` wherea,b, and c are integers.

REPRESENTING A TRIPLE
----

`new int[]{3, 4, 5}` to represent the tuple `(3, 4, 5)`.

FILTERING GOOD COMBINATIONS
---

```java
    filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
```

GENERATING TUPLES
---

```java
stream.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
      .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
```

GENERATING B VALUES
---

```java
IntStream.rangeClosed(1, 100)
         .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
         .boxed()
         .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
```

`mapToObj` of an `IntStream`, which returns an object-valued stream:
```java 
IntStream.rangeClosed(1, 100)
         .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
         .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});

```

GENERATING A VALUES
----

```java
Stream<int[]> pythagoreanTriples =
        IntStream.rangeClosed(1, 100)
                 .boxed()
                 .flatMap(a ->  IntStream.rangeClosed(a, 100)
                                         .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0) 
                                         .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                                         );
```

RUNNING THE CODE
---

```java
pythagoreanTriples.limit(5)
                  .forEach(t ->
                    System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
```

CAN YOU DO BETTER?
---

```java
Stream<int[]> pythagoreanTriples =
        IntStream.rangeClosed(1, 100)
                 .boxed()
                 .flatMap(a ->  IntStream.rangeClosed(a, 100)
                                         .mapToObj(b -> new double[]{a, b, (int)Math.sqrt(a * a + b * b)})
                                         .filter(t -> t[2] % 2 == 0)
                                         );
```

Building streams
--------------

Streams from values
----

```java
Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");  
stream.map(String::toUpperCase)
      .forEach(System.out::println);
```

```java
Stream<String> emptyStream = Stream.empty();
```

Streams from arrays
---

```java
int[] numbers = {2, 3, 5, 7, 11, 13}; 
int sum = Arrays.stream(numbers)
                .sum();
```

Streams from files
---

find out the number of unique words in a file as follows:

```java
long uniqueWords = 0;
try(Stream<String> lines = 
        Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
        uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                            .distinct()
                            .count();
        }
catch(IOException e){
}
```

Streams from functions: creating infinite streams!
---

* The Streams API provides two static methods to generate a stream from a function: `Stream.iterate` and `Stream.generate`.

```java
Stream.iterate(0, n -> n + 2)
      .limit(10)
      .forEach(System.out::println);
```

* This `iterate` operation is fundamentally sequential because the result depends on the previous application.

Fibonacci tuples series
---

```java
Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
```

GENERATE
---

* `generate` doesn’t apply successively a function on each new produced value.
* It takes a lambda of type `Supplier<T>` to provide new values.

```java
Stream.generate(Math::random)
      .limit(5)
      .forEach(System.out::println);
```

```java 
        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
        } };
        IntStream.generate(fib).limit(10).forEach(System.out::println);

```
