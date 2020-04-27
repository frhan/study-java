# Working with Enums
an _enum_ is a class that represents an enumeration. It is much better than a bunch of constants because it provides type‐safe checking. 

```java
public enum Season {
    WINTER, SPRING, SUMMER, FALL
}
```

```java
for(Season season: Season.values()) { 
    System.out.println(season.name() + " " + season.ordinal());
}
```
*  You can’t compare an `int` and `enum` value directly anyway. 

### Using Enums in Switch Statements

* you can’t extend an `enum`.

```java
public enum ExtendedSeason extends Season { } // DOES NOT COMPILE
```
* The following code does not compile:

```java
Season season = Season.FALL;
        switch (season){
            case Season.FALL:
}
```
### Adding Constructors, Fields, and Methods
```java
public enum Season {
2:  WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");
3:  private String expectedVisitors;
4:  private Season(String expectedVisitors) {
5:      this.expectedVisitors = expectedVisitors;
6:  }
7:  public void printExpectedVisitors() {
8:      System.out.println(expectedVisitors);
9: } }

```
* The constructor is `private` because it can only be called from within the `enum`. The code will not compile with a `public` constructor.

* The first time that we ask for any of the enum values, Java constructs all of the enum values. After that, Java just returns the already‐constructed enum values.

```java
public enum OnlyOne { 
    ONCE(true);
    private OnlyOne(boolean b) { System.out.println("constructing");
}

public static void main(String[] args) {
    OnlyOne firstCall = OnlyOne.ONCE; // prints constructing
    OnlyOne secondCall = OnlyOne.ONCE; // doesn't print anything 
    
}
}
```
