# Coding `equals`, `hashCode`, and `toString`

### `toString`

```java
System.out.println(new ArrayList()); // []
System.out.println(new String[0]); // [Ljava.lang.String;@65cc892e
```
`ArrayList` provided an implementation of `toString()` that listed the contents of the `ArrayList`.

### `equals`

Checking if two objects are equivalent uses the `equals()` method, or at least it does if the developer implementing the method overrides equals().

String does have an `equals()` method. It checks that the values are the same.

The Contract for `equals()` methods: 

1. It is _reflexive_: For any non‐null reference value x, `x.equals(x)` should return `true`.

2. It is _symmetric_: For any non‐null reference values x and y, `x.equals(y)` should return `true` if and only if `y.equals(x)` returns true.
3. It is _transitive_: For any non‐null reference values x, y, and z, if `x.equals(y` returns `true` and `y.equals(z)` returns true, then `x.equals(z)` should return `true`.

4. It is _consistent_: For any non‐null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.

5. For any non‐null reference value x, x.equals(null) should return false.

### `hashCode`
A _hash code_ is a number that puts instances of a class into a finite number of categories.

three points:

1. Within the same program, the result of `hashCode()` must not change. 
2. If `equals()` returns true when called with two objects, calling `hashCode()` on each of those objects must return the same result.This means hashCode() can use a subset of the variables that equals() uses.
3. If equals() returns false when called with two objects, calling hashCode() on each of those objects does not have to return a different result.

