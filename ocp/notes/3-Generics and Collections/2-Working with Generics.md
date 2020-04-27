## Working with Generics
_Generics_  allow us to write and use parameterized types.

```java
List<String> names = new ArrayList<String>();
names.add(new StringBuilder("Webby")); // DOES NOT COMPILE
```

### Generic Classes

The syntax for introducing a generic is to declare a _formal type parameter_ in angle brackets.

```java
public class Crate<T> { 
    private T contents; 
    public T emptyCrate() {
        return contents; 
        }
    public void packCrate(T contents) {
         this.contents = contents;
    } 
}
```

Generic classes aren’t limited to having a single type parameter. This class shows two generic parameters:

```java
public class SizeLimitedCrate<T, U> {
    private T contents;
    private U sizeLimit;

    public SizeLimitedCrate(T contents, U sizeLimit) {
        this.contents = contents;
        this.sizeLimit = sizeLimit; 
    }
}
```

### Generic Interfaces

```java
public interface Shippable<T> { 
    void ship(T t);
}
```
- Create a static variable as a generic type parameter. This is not allowed because the type is linked to the instance of the class.

### Generic Methods
the method uses a generic parameter:

```java
public static <T> Crate<T> ship(T t) { 
    System.out.println("Preparing " + t); 
    return new Crate<T>();
}
```

### Interacting with Legacy Code
* using generics gives us compile time safety

### Bounds

A _bounded parameter_ type is a generic type that specifies a bound for the generic.

A _wildcard generic_ type is an unknown generic type represented with a question mark (?).

#### Unbounded Wildcards
An unbounded wildcard represents any data type. You use ? when you want to specify that any type is OK with you

```java
public static void printList(List<?> list) { 
    for (Object x: list) System.out.println(x);
}
public static void main(String[] args) {
    List<String> keywords = new ArrayList<>(); keywords.add("java");
    printList(keywords);
}
```
- `printList()` takes any type of list as a parameter. 

#### Upper-Bounded Wildcards

```java
ArrayList<Number> list = new ArrayList<Integer>(); // DOES NOT COMPILE
```
Instead, we need to use a wildcard:
```java
List<? extends Number> list = new ArrayList<Integer>();
```

```java
static class Sparrow extends Bird { } 
static class Bird { }

List<? extends Bird> birds = new ArrayList<Bird>(); 
birds.add(new Sparrow()); // DOES NOT COMPILE 
birds.add(new Bird()); // DOES NOT COMPILE
```

We have an interface and two classes that implement it:

```java
interface Flyer { void fly(); }
class HangGlider implements Flyer { 
    public void fly() {} 
} 
class Goose implements Flyer { 
    public void fly() {} 
}
```
- _Upper bounds_ are like anonymous classes in that they use extends regardless of whether we are working with a class or an interface.

#### Lower-Bounded Wildcards

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-07%20at%202.29.26%20PM.png)

### Putting It All Together

```java
class A {}
class B extends A { }
class C extends B { }
```

```java
List<?> list1 = new ArrayList<A>();
List<? extends A> list2 = new ArrayList<A>();
List<? super A> list3 = new ArrayList<A>();
List<? extends B> list4 = new ArrayList<A>(); // DOES NOT COMPILE
List<? super B> list5 = new ArrayList<A>();
List<?> list6 = new ArrayList<? extends A>(); // DOES NOT COMPILE
```
