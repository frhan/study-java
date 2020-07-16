# Using Concurrent Collections

## Introducing Concurrent Collections
using the concurrent collections is extremely convenient in practice. It also prevents us from introducing mistakes in own custom implementation, such as if we forgot to synchronize one of the accessor methods. In fact, the concurrent collections often include performance enhancements that avoid unnecessary synchronization.

`private Map<String,Object> foodData = new ConcurrentHashMap<String,Object>();`

## Understanding Memory Consistency Errors
A memory consistency error occurs when two threads have inconsistent views of what should be the same data

When two threads try to modify the same non-concurrent collection, the JVM may throw a ConcurrentModificationException at runtime.

```java
Map<String, Object> foodData = new HashMap<String, Object>(); 
foodData.put("penguin", 1);
foodData.put("flamingo", 2);
for(String key: foodData.keySet())
    foodData.remove(key);
```

this snippet will throw a ConcurrentModificationException at runtime, since the iterator `keyset()` is not properly updated after the first element is removed. 

```java
Map<String, Object> foodData = new ConcurrentHashMap<String, Object>(); 
foodData.put("penguin", 1);
foodData.put("flamingo", 2);
for(String key: foodData.keySet())
    foodData.remove(key);
```
the ConcurrentHashMap is ordering read/write access such that all access to the class is consistent.

## Working with Concurrent Classes
![alt text ](https://github.com/study-java/ocp/blob/master/images/7_5.png)

## Understanding `Blocking` Queues
the `BlockingQueue` is just like a regular Queue, except that it includes methods that will wait a specific amount of time to complete an operation.

![alt text ](https://github.com/frhan/study-java/blob/master/images/7_6.png)

![alt text ](https://github.com/frhan/study-java/blob/master/images/7_7.png)

![alt text ](https://github.com/frhan/study-java/blob/master/images/7_8.png)

![alt text ](https://github.com/frhan/study-java/blob/master/images/7_9.png)

## Understanding `SkipList` Collections
The `SkipList` classes, `ConcurrentSkipListSet` and `ConcurrentSkipListMap`, are concurrent versions of their sorted counterparts, `TreeSet` and `TreeMap` , respectively

- When you see `SkipList` or `SkipSet` on the exam, just think “sorted” concurrent collections and the rest should follow naturally.
- it is recommended that you assign these objects to interface references, such as `SortedMap` or `NavigableSet`

## Understanding `CopyOnWrite` Collections
`CopyOnWriteArrayList` and `CopyOnWriteArraySet`

- These classes copy all of their elements to a new underlying structure anytime an element is added, modified, or removed from the collection.
- By a modified element, we mean that the reference in the collection is changed.
- Any iterator established prior to a modification will not see the changes, but instead it will iterate over the original elements prior to the modification.
- The CopyOnWrite classes can use a lot of memory, since a new collection structure needs be allocated anytime the collection is modified.

```java
List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4,3,52));
for(Integer item: list) {
    System.out.print(item+" ");
    list.add(9);
}
```
## Obtaining Synchronized Collections