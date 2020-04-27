# Connecting to a Database

## Building a JDBC URL
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-10%20at%201.37.35%20AM.png)
### Getting a Database Connection
- There are two main ways to get a `Connection`: 
`DriverManager` or `DataSource`. 

    - `DriverManager` is the one covered on the exam. Do not use a `DriverManager` in code someone is paying you to write. 

    - A `DataSource` is a factory, and it has more features than `DriverManager`
    
```java
import java.sql.*;
public class TestConnect {
public static void main(String[] args) throws SQLException { 
    Connection conn = DriverManager.getConnection("jdbc:derby:zoo"); System.out.println(conn);
} 
}

```
- a `DataSource` maintains a connection pool so that you can keep reusing the same connection rather than needing to get a new one each time.

- When `Class.forName()` is used, the error about an invalid class occurs on that line and throws a `ClassNotFoundException`:

```java
    Class.forName("not.a.driver");
```

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-10%20at%201.41.49%20AM.png)
