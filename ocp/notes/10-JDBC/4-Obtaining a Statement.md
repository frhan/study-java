# Obtaining a Statement

`Statement stmt = conn.createStatement();`

```java
Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
```
## Choosing a `ResultSet` Type
- a `ResultSet` is in `TYPE_FORWARD_ONLY` mode
- Two other modes that you can request when creating a Statement are `TYPE_SCROLL_ INSENSITIVE` and `TYPE_SCROLL_SENSITIVE`.
- The difference between `TYPE_SCROLL_INSENSITIVE` and `TYPE_SCROLL_SENSITIVE` is what happens when data changes in the actual database while you are busy scrolling.
- With `TYPE_ SCROLL_INSENSITIVE`, you have a static view of what the `ResultSet` looked like when you did the query. If the data changed in the table, you will see it as it was when you did the query. 
- With `TYPE_SCROLL_SENSITIVE`, you would see the latest data when scrolling through the `ResultSet`.

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-10%20at%206.53.43%20PM.png)
## Choosing a `ResultSet` Concurrency Mode
- By default, a `ResultSet` is in `CONCUR_READ_ONLY` mode.
- It means that you can’t update the result set  
- There is one other mode that you can request when creating a Statement.it lets you modify the database through the ResultSet. It is called `CONCUR_UPDATABLE`
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-10%20at%206.55.33%20PM.png)
