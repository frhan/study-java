## Working with Dates and Times
### Creating Dates and Times

`LocalDate`: Contains just a date—no time and no time zone.

`LocalTime`: Contains just a time—no date and no time zone.

`LocalDateTime`: Contains both a date and time but no time zone.

`ZonedDateTime`: Contains a date, time, and time zone. 

_Greenwich Mean Time_ is a time zone in Europe that is used as time zone zero when discussing offsets.

_UTC_ uses the same time zone zero as _GMT_.

```java
public static LocalDate of(int year, int month, int dayOfMonth) 
public static LocalDate of(int year, Month month, int dayOfMonth)
```
* For months in the new date and time methods, Java counts starting from 1, just as we humans do.

```java
public static LocalTime of(int hour, int minute)
public static LocalTime of(int hour, int minute, int second)
public static LocalTime of(int hour, int minute, int second, int nanos)
```

```java
public static ZonedDateTime of(int year, int month,int dayOfMonth, int hour, int minute, int second, int nanos, ZoneId zone)
public static ZonedDateTime of(LocalDate date, LocalTime time, ZoneId zone)
public static ZonedDateTime of(LocalDateTime dateTime, ZoneId zone)
```

### Manipulating Dates and Times

* The date and time classes are immutable.

```java
LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
date.plusDays(2);
date.plusWeeks(1);
```

```java
LocalTime time = LocalTime.of(5, 15);
time.plusMinutes(5);
```

### Working with Periods

```java
Period annually = Period.ofYears(1);
Period quarterly = Period.ofMonths(3);
Period everyThreeWeeks = Period.ofWeeks(3); Period everyOtherDay = Period.ofDays(2); Period everyYearAndAWeek = Period.of(1, 0, 7);
```

```java
Period.of(1,2,3); //P1Y2M3D
```

### Working with Durations

- For `Duration`, you can specify the number of days, hours, minutes, seconds, or nanoseconds

### Accounting for Daylight Savings Time
