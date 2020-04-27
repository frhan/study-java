# Getting Data from a ResultSet

## Reading a ResultSet

```java
Map<Integer, String> idToNameMap = new HashMap<>();
ResultSet rs = stmt.executeQuery("select id, name from species"); while(rs.next()) {
int id = rs.getInt("id");
String name = rs.getString("name"); idToNameMap.put(id, name);
}
System.out.println(idToNameMap); // {1=African Elephant, 2=Zebra}
```
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-10%20at%2010.59.18%20PM.png)
- Always use an `if` statement or `while` loop when calling `rs.next()`.
- Column indexes begin with 1.

## Getting Data for a Column
# table 10.6
## Scrolling ResultSet
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-10%20at%2011.01.57%20PM.png)

## Closing Database Resources
- Closing a `Connection` also closes the `Statement` and `ResultSet`. 
- Closing a `Statement` also closes the `ResultSet`.

## Dealing with Exceptions
