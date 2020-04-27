Item 38: Check parameters for validity
------
* If an invalid parameter value is passed to a method and the method checks its parameters before execution, it will fail quickly and cleanly with an appropriate exception.

```java
/**
* Returns a BigInteger whose value is (this mod m). This method
* differs from the remainder method in that it always returns a * non-negative BigInteger.
*
* @param m the modulus, which must be positive
* @return this mod m
* @throws ArithmeticException if m is less than or equal to 0 */
  public BigInteger mod(BigInteger m) { if (m.signum() <= 0)
    throw new ArithmeticException("Modulus <= 0: " + m);
       ... // Do the computation
  }
```

* nonpublic methods should generally check their parameters using assertions

``` java
// Private helper function for a recursive sort
private static void sort(long a[], int offset, int length) {
assert a != null;
assert offset >= 0 && offset <= a.length;
assert length >= 0 && length <= a.length - offset; ... // Do the computation
}
```

* you should design methods to be as general as it is practi- cal to make them. The fewer restrictions that you place on parameters, the better, assuming the method can do something reasonable with all of the parameter values that it accepts.

Summary: each time you write a method or constructor, you should think about what restrictions exist on its parameters. You should document these restrictions and enforce them with explicit checks at the beginning of the method body. It is important to get into the habit of doing this. The modest work that it entails will be paid back with interest the first time a validity check fails.
