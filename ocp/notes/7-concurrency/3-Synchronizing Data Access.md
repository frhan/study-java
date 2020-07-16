# Synchronizing Data Access
the unexpected result of two tasks executing at the same time is referred to as a _race condition_.

## Protecting Data with Atomic Classes

_Atomic_ is the property of an operation to be carried out as a single unit of execution without any interference by another thread. 

Any thread trying to access the sheepCount variable while an atomic operation is in process will have to wait until the atomic operation on the variable is complete

Concurrency API includes numerous useful classes that are conceptually the same as our primitive classes but that support atomic operations.

![alt text ](https://github.com/frhan/study-java/blob/master/images/7_3.png)

![alt text ](https://github.com/frhan/study-java/blob/master/images/7_4.png)

## Improving Access with Synchronized Blocks
the unexpected result of two tasks executing at the same time is referred to as a _race condition_.

synchronized the creation of the threads :

```java
SheepManager manager = new SheepManager(); 
synchronized(manager) {
// Work to be completed by one thread at a time 
}
```

synchronized the execution of the threads :

```java
private void incrementAndReport() { 
    synchronized(this) {
        System.out.print((++sheepCount)+" "); 
    }
}
```

We could synchronized on any object, so long as it was the same object. 
For example, the following code snippet would have also worked:

```java
private final Object lock = new Object(); 
private void incrementAndReport() {
    synchronized(lock) { 
        System.out.print((++sheepCount)+" ");
    } 
}
```

Although we didn’t need to make the lock variable final, doing so ensures that it is not reassigned after threads start using it.

## Synchronizing Methods

We can add the synchronized modifier to any instance method to synchronize automatically on the object itself. 

```java
private void incrementAndReport() { 
    synchronized(this) {
        System.out.print((++sheepCount)+" "); 
    }
}
```

```java
private synchronized void incrementAndReport() { 
    System.out.print((++sheepCount)+" ");
}
```

We can also add the `synchronized` modifier to static methods.
What object is used as the monitor when we synchronize on a static method? The class object, of course!

```java
public static void printDaysWork() { 
    synchronized(SheepManager.class) {
        System.out.print("Finished work"); 
    }
}
```

```java
public static synchronized void printDaysWork() { 
    System.out.print("Finished work");
}
```

## Understanding the Cost of Synchronization
While multi-threaded programming is about doing multiple things at the same time, _synchronization_ is about taking multiple threads and making them perform in a more single-threaded manner.

Synchronization is about protecting data integrity at the cost of performance. In many cases, performance costs are minimal, but in extreme scenarios the application could
