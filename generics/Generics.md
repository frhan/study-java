Generics
-------

Generics add stability to your code by making more of your bugs detectable at compile time.

Why Use Generics?
---
* Stronger type checks at compile time.
* Elimination of casts.
* Enabling programmers to implement generic algorithms.


Generic Types
---

**A Simple Box Class**
```java
public class Box {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}
```

**A Generic Version of the Box Class**

A generic class is defined with the following format:

class name<T1, T2, ..., Tn> { /* ... */ }
