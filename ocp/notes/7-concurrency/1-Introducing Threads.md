## Introducing Threads

_shared environment_, we mean that the threads in the same process share the same memory space and can communicate directly with one another.

A _task_ is a single unit of work performed by a thread.

### Distinguishing Thread Types

A _system_ thread is created by the JVM and runs in the background of the application

a _user-defined_ thread is one created by the application developer to accomplish a specific task. 


a _daemon_ thread is one that will not prevent the JVM from exiting when the program finishes.

### Understanding Thread Concurrency

The property of executing multiple threads and processes at the same time is referred to as concurrency.

Operating systems use a _thread scheduler_ to determine which threads should be cur- rently executing.

A _context switch_ is the process of storing a thread’s current state and later restoring the state of the thread to continue execution.

A _thread priority_ is a numeric value associated with a thread that is taken into consideration by the thread scheduler when determining which threads should currently be executing.

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-04%20at%208.42.20%20PM.png)

### Introducing Runnable

_Runnable_ for short, is a functional interface that takes no arguments and returns no data.

```java
@FunctionalInterface 
public interface Runnable { 
    void run();
}
```
lambda expressions each rely on the Runnable interface:

```java
() -> System.out.println("Hello World")
```

### Creating a Thread

a Thread instance will execute can be done two ways in Java:
- Provide a Runnable object or lambda expression to the Thread constructor.
- Create a class that extends Thread and overrides the run() method.

- be careful about cases where a Thread or Runnable is created but no `start()` method is called.

### Polling with Sleep

`Thread.sleep()` method to implement polling. The `Thread.sleep()` method requests the current thread of execution rest for a specified number of milliseconds.
