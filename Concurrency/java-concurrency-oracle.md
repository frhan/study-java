Concurrency
===

Processes and Threads
---

Processes
---

* has a self-contained execution environment
* has a complete, private set of basic run-time resources
* has its own memory space.

Threads
---

* _lightweight processes_.
* creating a new thread requires fewer resources than creating a new process
* Threads share the process's resources, including memory and open files

Thread Objects
---

two basic strategies for using `Thread` objects to create a concurrent application.

* instantiate `Thread` each time the application needs to initiate an asynchronous task.
* pass the application's tasks to an executor.

Defining and Starting a Thread
---

_Provide a Runnable object._

```java
public class HelloRunnable implements Runnable {
    ...
    ...
}
```

_Subclass Thread._

```java 
public class HelloThread extends Thread {
    ...
    ...
}
```
Which of these idioms should you use? The first idiom, which employs a Runnable object, is more general, because the Runnable object can subclass a class other than Thread.

Pausing Execution with Sleep
---

* `Thread.sleep` causes the current thread to suspend execution for a specified period.
* `sleep` times are not guaranteed to be precise, because they are limited by the facilities provided by the underlying OS
* it throws `InterruptedException`.