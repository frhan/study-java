### HashMap
`HashMap` supports adding `null` key as well as `null` values but `ConcurrentHashMap` does 
not. Inserting `null` key or `null` in a `ConcurrentHashMap` will throw a `NullPointerException`. 

### NavigableMap

A `NavigableMap` is a `SortedMap` (which in turn extends Map) extended with navigation methods returning 
the closest matches for given search targets. Methods `lowerEntry`, `floorEntry`, `ceilingEntry`, and `higherEntry`
return `Map`. Entry objects associated with keys respectively less than, less than or equal, greater than or equal,
and greater than a given key, returning `null` if there is no such key. Similarly, methods `lowerKey`, `floorKey`, 
`ceilingKey`, and `higherKey` return only the associated keys. 

- All of these methods are designed for locating, not traversing entries. 

- A `NavigableMap` may be accessed and traversed in either ascending or descending key order. 
  The descendingMap method returns a view of the map with the senses of all relational and directional methods 
  inverted. The performance of ascending operations and views is likely to be faster than that of descending ones. 
  Methods `subMap`, `headMap`, and `tailMap` differ from the like-named `SortedMap` methods in accepting additional 
  arguments describing whether lower and upper bounds are inclusive versus exclusive. 
  `Submaps` of any `NavigableMap` must implement the `NavigableMap` interface. 

- This interface additionally defines methods `firstEntry`, `pollFirstEntry`, `lastEntry`, and `pollLastEntry` 
  that return and/or remove the least and greatest mappings, if any exist, else returning null. 

- Implementations of entry-returning methods are expected to return `Map.Entry` pairs representing snapshots 
  of mappings at the time they were produced, and thus generally do not support the optional `Entry.setValue` method.
  Note however that it is possible to change mappings in the associated map using method put. 

 - Methods `subMap(K, K)`, `headMap(K)`, and `tailMap(K)` are specified to return `SortedMap` to allow 
  existing implementations of `SortedMap` to be compatibly retrofitted to implement `NavigableMap`, but 
  extensions and implementations of this interface are encouraged to override these methods to return 
  `NavigableMap`. Similarly, `SortedMap.keySet()` can be overridden to return `NavigableSet`.

### Deque
`Deque` is an important interface for the exam. To answer the questions, you must remember that a 
`Deque` can act as a `Queue` as well as a `Stack`. Based on this fact, you can deduce the following: 
 
 1. Since `Queue` is a FIFO structure (First In First Out i.e. add to the end and remove from the front), it has
 methods `offer(e)`/`add(e)`(for adding an element to the end or tail) and `poll()`/`remove()`(for removing 
 an element from the front or head) for this purpose.  Note that offer and add are similar while `poll` and 
 `remove` are similar.  
 
 2. Since `Stack` is a LIFO structure (Last In First Out i.e. add to the front and remove from the front), 
 it provides methods `push(e)` and `pop()` for this purpose, where `push` adds to the front and `pop` removes 
 from the front.  Besides the above methods, `Deque` also has variations of the above methods. 
 But it is easy to figure out what they do:  
    
    * `pollFirst()`/`pollLast()` - `poll` is a `Queue` method. Therefore `pollFirst` and `pollLast` will 
    remove elements from the front and from the end respectively. 
    
    * `removeFirst()`/`removeLast()` - These are Deque specific methods. They will remove elements from 
    the front and from the end respectively. These methods differ from `pollFirst`/`pollLast` only in 
    that they throw an exception if this `deque` is empty.  
    
    * `offerFirst(e)`/`offerLast(e)` - offer is a `Queue` method. Therefore `offerFirst` and `offerLast` will 
    add elements to the front and to the end respectively. 
    
    * `addFirst(e)`/`addLast(e)` - add is a `Queue` method. Therefore `addFirst` and `addLast` will add elements
     to the front and to the end respectively.  
    
    * `peek()`, `peekFirst()`: return the first element from the front of the queue but does not remove it from
     the queue. 
    
    * `peekLast()` : returns the last element from the end of the queue but does not remove it from the queue. 
    
    * `element()`: retrieves, but does not remove, the head of the queue represented by this deque 
    (in other words, the first element of this deque). This method differs from peek only in that it throws an 
    exception if this deque is empty.  
    
    Notice that there is no `pushFirst(e)` and `pushLast(e)`. You may wonder why there are multiple methods for 
    the same thing such as `offer(e)` and `add(e)`. Well, they are not exactly same. `add(e)` throws an exception 
    if the element cannot be added to the queue because of lack of capacity, while `offer(e)` does not. 
    There are similar differences in other methods but they are not too important for the exam. 

* Arrays.sort works on arrays not lists.

* The second argument to `Collections.sort` is for passing a `Comparator` object that you want to use for 
comparing the objects. If it is `null`, natural sorting order for the elements is used. Therefore, the given 
list will be sorted with "andy" as the first element, which will be printed.

`SortedSet`: A `SortedSet` is a Set that maintains its elements in ascending order. Several additional 
operations are provided to take advantage of the ordering.The `SortedSet` interface is used for things 
like word lists and membership rolls. `TreeSet` is a commonly used `SortedSet` implementation.  

`SortedMap`: A `SortedMap` is a `Map` that maintains its mappings in ascending key order. It is the Map 
analogue of SortedSet. The `SortedMap` interface is used for apps like dictionaries and telephone directories. 
`TreeMap` is a commonly used `SortedMap` implementation.
 
 `Collections.sort`
 
 `Collections.sort` is for passing a `Comparator` object that you want to use for comparing the objects. 
 If it is `null`, natural sorting order for the elements is used. 
  ```
  Collections.sort(array,null);
  ```
  
- `Arrays.sort` works on `arrays` not `lists`

