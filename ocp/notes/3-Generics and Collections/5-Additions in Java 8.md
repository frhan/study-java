# Additions in Java 8

### Using Method References
_Method references_ are a way to make the code shorter by reducing some of the code that can be inferred and simply mentioning the name of the method.

There are four formats for method references:
* Static methods
* Instance methods on a particular instance
* Instance methods on an instance to be determined at runtime
* Constructors

#### Static methods

```java
Consumer<List<Integer>> methodRef1 = Collections::sort;
Consumer<List<Integer>> lambda1 = l -> Collections.sort(l);
```

#### Instance methods 

```java
String str = "abc";
Predicate<String> methodRef2 = str::startsWith;
Predicate<String> lambda2 = s -> str.startsWith(s);
```

an instance method without knowing the instance in advance:

```java
Predicate<String> methodRef3 = String::isEmpty;
Predicate<String> lambda3 = s -> s.isEmpty();
```
Constructors:

A _constructor reference_ is a special type of method reference that uses new instead of a method, and it creates a new object.

```java
Supplier<ArrayList> methodRef4 = ArrayList::new;
Supplier<ArrayList> lambda4 = () -> new ArrayList();
```
### Removing Conditionally

```java
boolean removeIf(Predicate<? super E> filter)
```
```java
list.removeIf(s -> s.startsWith("A"));
```
### Updating All Elements

```java
void replaceAll(UnaryOperator<E> o)
```

```java
list.replaceAll(x -> x*2);
```
### Looping through a Collection
```java
List<String> cats = Arrays.asList("Annie", "Ripley");
cats.forEach(c -> System.out.println(c));
cats.forEach(System.out::println); // method reference
```
### Using New Java 8 Map APIs

#### `merge`

Implementation Requirements:
The _default_ implementation is equivalent to performing the following steps for this `map`, then returning the current value or `null` if absent:

```java
 V oldValue = map.get(key);
 V newValue = (oldValue == null) ? value :
              remappingFunction.apply(oldValue, value);
 if (newValue == null)
     map.remove(key);
 else
     map.put(key, newValue);
````
