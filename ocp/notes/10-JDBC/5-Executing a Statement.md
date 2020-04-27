## Executing a Statement
- statement that change the data in a table - `executeUpdate()`
- `SQL UPDATE` statement is not the only statement that uses this method.

```java
Statement stmt = conn.createStatement(); 
int result = stmt.executeUpdate("insert into species values(10, 'Deer', 3)");
```

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-10%20at%2010.57.29%20PM.png)
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-10%20at%2010.57.34%20PM.png)
