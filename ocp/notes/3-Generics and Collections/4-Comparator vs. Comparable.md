### Comparable

```java
public interface Comparable<T> { 
    public int compareTo(T o);
}
```

There are three rules to know:
* The number zero is returned when the current object is equal to the argument to `compareTo()`.

* A number less than zero is returned when the current object is smaller than the argument to `compareTo()`.

* A number greater than zero is returned when the current object is larger than the argument to `compareTo()`.

**Remember that `id – a.id` sorts in ascending order and `a.id – id` sorts in descending order.**

### Comparator

```java
Comparator<Duck> byWeight = new Comparator<Duck>() {
    public int compare(Duck d1, Duck d2) {
        return d1.getWeight()—d2.getWeight();
    }
};
```

```java
Comparator<Duck> byWeight = (d1, d2) -> d1.getWeight() — d2.getWeight();
```
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-10%20at%208.01.37%20PM.png)
