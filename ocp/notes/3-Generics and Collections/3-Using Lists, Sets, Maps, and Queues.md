# Using Lists, Sets, Maps, and Queues
There are four main interfaces in the Java Collections Framework:
`List`: A _list_ is an ordered collection of elements that allows duplicate entries. 
Elements in a list can be accessed by an `int` index.
`Set`: A _set_ is a collection that does not allow duplicate entries.
`Queue`: A _queue_ is a collection that orders its elements in a specific order for processing.
`Map`: A _map_ is a collection that maps keys to values, with no duplicate keys allowed. The elements in a map are key/value pairs.

### Common Collections Methods

#### `add()`

```java
boolean add(E element)
```

```java
List<String> list = new ArrayList<>();
System.out.println(list.add("Sparrow")); // true
```
```java
Set<String> set = new HashSet<>();
System.out.println(set.add("Sparrow")); // true
System.out.println(set.add("Sparrow")); // false
```
- A `Set` does not allow duplicates

#### `remove()`
The `remove()` method removes a single matching value in the Collection and returns whether it was successful.

```java
boolean remove(Object object)
```
- Since calling remove() with an int uses the index, an index that doesn’t exist will throw an exception.  `birds.remove(100)`; throws an `IndexOutOfBoundsException`.

#### `isEmpty() and size()`

```java
boolean isEmpty() 
int size()
```

#### `clear()`
The clear() method provides an easy way to discard all elements of the Collection. The method signature is

```java
void clear()
```

#### `contains()`
The `contains()` method checks if a certain value is in the Collection. The method signature is

```java
boolean contains(Object object)
```
- This method calls `equals()` on each element of the `ArrayList` to see if there are any matches.

### Using the List Interface
The main thing that all `List` implementations have in common is that they are ordered and allow duplicates.

#### Comparing List Implementations

`ArrayList`

An `ArrayList` is like a resizable array. When elements are added, the `ArrayList` auto- matically grows. When you aren’t sure which collection to use, use an `ArrayList`

* The main benefit of an `ArrayList` is that you can look up any element in constant time.
* Adding or removing an element is slower than accessing an element

`LinkedList`

* A `LinkedList` is special because it implements both `List` and `Queue`. It has all of the methods of a `List`. It also has additional methods to facilitate adding or removing from the beginning and/or end of the list.

* The main benefits of a LinkedList are that you can access, add, and remove from the beginning and end of the list in constant time.

`Vector`

* `Vector` does the same thing as `ArrayList` except more slowly. The benefit to that decrease in speed is that it is thread-safe.

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-07%20at%205.03.32%20PM.png)

### Using the Set Interface
The main thing that all Set implementa- tions have in common is that they do not allow duplicates.

#### Comparing Set Implementations

`HashSet`

A `HashSet` stores its elements in a hash table. This means that it uses the `hashCode()` method of the objects to retrieve them more efficiently.

The main benefit is that adding elements and checking if an element is in the set both have constant time.

`TreeSet`

A `TreeSet` stores its elements in a sorted tree structure. 
The tradeoff is that adding and checking if an element is present are both O(log n)

#### Working with Set Methods

```java
Set<Integer> set = new HashSet<>();
boolean b1 = set.add(66);
```
The `hashCode()` method is used to know which bucket to look in so that Java doesn’t have to look through the whole set to find out if an `object` is there. The best case is that hash codes are unique, and Java has to call `equals()` on only one object. 

**The `NavigableSet` interface:**

`TreeSet` implements the `NavigableSet` interface.
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-10%20at%205.40.17%20PM.png)


Just remember that lower and higher elements do not include the target element.

### Using the Queue Interface
we use a queue when elements are added and removed in a specific order. Queues are typically used for sorting elements prior to processing them.

#### Comparing Queue Implementations
A double-ended queue is different from a regular queue in that you can insert and remove elements from both the front and back of the queue.

The main benefit of a `LinkedList` is that it implements both the `List` and `Queue` interfaces.

An `ArrayDeque` is a “pure” double-ended queue.The main benefit of an `ArrayDeque` is that it is more efficient than a `LinkedList`.


#### Working with _`Queue`_ Methods

When talking about LIFO (stack), people say `push/poll/peek`. When talking about FIFO (single-ended queue), people say `offer/poll/peek`.
```java
12: Queue<Integer> queue = new ArrayDeque<>();
13: System.out.println(queue.offer(10)); // true
14: System.out.println(queue.offer(4)); // true
15: System.out.println(queue.peek()); // 10
16: System.out.println(queue.poll()); // 10
17: System.out.println(queue.poll()); // 4
18: System.out.println(queue.peek()); // null
```
### Map

#### Comparing Map Implementations

A `HashMap` stores the keys in a hash table. This means that it uses the `hashCode()` method of the keys to retrieve their values more efficiently.

A `TreeMap` stores the keys in a sorted tree structure.

A `Hashtable` is like `Vector` in that it is really old and thread-safe and that you won’t be expected to use it.

#### Working with Map Methods

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-10%20at%206.58.29%20PM.png)

### Comparing Collection Types
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-10%20at%207.07.43%20PM.png)
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-10%20at%207.07.49%20PM.png)

The data structures that involve sorting do not allow `nulls`.
This means that `TreeSet` cannot contain `null` elements. It also means that `TreeMap` cannot contain `null` keys.

`TreeMap` — no `null` keys

`Hashtable` — no `null` keys or values

`TreeSet` — no `null` elements

`ArrayDeque` — no `null` elements

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-10%20at%207.13.24%20PM.png)

# `Comparator` vs. `Comparable`
**Remember that numbers sort before letters and uppercase letters sort before lowercase letters.**

