Passing code with behavior parameterization
----------

 _Behavior parameterization_ is a software development pattern that lets you handle frequent requirement changes.

  Lambda expres- sions in Java 8 tackle the problem of verbosity.

First attempt: filtering green apples
---

```java
public static List<Apple> filterGreenApples(List<Apple> inventory) {
  List<Apple> result = new ArrayList<>();
  for(Apple apple: inventory){
    if( "green".equals(apple.getColor() ) {
      result.add(apple);
    }
  }
  return result;
}
```

Second attempt: parameterizing the color
---

```java
public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory){
      if ( apple.getColor().equals(color) ) {
         result.add(apple);
      }
    }
    return result;
}
```

Third attempt: filtering with every attribute you can think of
------

```java
public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple: inventory){
      if ( (flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight) ){
            result.add(apple);
        }
    }
    return result;
}
```

Behavior parameterization
-------

Fourth attempt: filtering by abstract criteria
----

```java
public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
  List<Apple> result = new ArrayList<>();
  for(Apple apple: inventory){
    if(p.test(apple)){
      result.add(apple);
    }
   }
   return result;
}
```

Fifth attempt: using an anonymous class
---
```java
  List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
      public boolean test(Apple apple){
        return "red".equals(apple.getColor());
      });
  }
```

Sixth attempt: using a lambda expression
---
```java
List<Apple> result = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
```

Seventh attempt: abstracting over List type
---
```java
public interface Predicate<T>{
  boolean test(T t);
}

public static <T> List<T> filter(List<T> list, Predicate<T> p){     
  List<T> result = new ArrayList<>();
  for(T e: list){
    if(p.test(e)){
      result.add(e);
    }
  }
  return result;
}
```
**using lambda expressions:**

```java
List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
List<String> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
```

Real-world examples
---

**Sorting with a Comparator**

using an anonymous class:
```java
inventory.sort(new Comparator<Apple>() {
  public int compare(Apple a1, Apple a2){
    return a1.getWeight().compareTo(a2.getWeight());
  }
});
```
**With a lambda expression:**
```java
inventory.sort(
            (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
```
**Executing a block of code with Runnable**

```java
Thread t = new Thread(new Runnable() {
  public void run(){
    System.out.println("Hello world");
  }
});
```
With a lambda expression:
```java
Thread t = new Thread(() -> System.out.println("Hello world"));
```
**GUI event handling**

```java
button.setOnAction((ActionEvent event) -> label.setText("Sent!!"));
```
