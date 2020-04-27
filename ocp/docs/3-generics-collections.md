# Generics and Collections


# Searching and Sorting


# To Remeber
* `HashSet` does not guarantee any iteration order.
```java
Set<Number> set = new HashSet<>();
set.add(null); //compile successfully
Iterator it = set.iterator();
while(it.hasNext())
    System.out.println(it.next());
```

*