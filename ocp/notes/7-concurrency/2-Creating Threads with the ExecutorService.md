# Creating Threads with the `ExecutorService`
The Concurrency API includes the `Executors` factory class that can be used to create instances of the `ExecutorService` object.

```java
ExecutorService service = null; 
try {
    service = Executors.newSingleThreadExecutor();
    System.out.println("begin");
    service.execute(() -> System.out.println("Printing zoo inventory")); service.execute(() -> {for(int i=0; i<3; i++)
    System.out.println("Printing record: "+i);} );
    service.execute(() -> System.out.println("Printing zoo inventory"));
    System.out.println("end"); 
} finally {
    if(service != null) 
        service.shutdown();
}

```
With a single-thread executor, results are guaranteed to be executed in the order in which they are added to the executor service. 


## Shutting Down a Thread Executor

![alt text ](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-04%20at%208.58.27%20PM.png)

- `shutdown()` does not actually stop any tasks that have already been submitted to the thread executor

want to cancel all running and upcoming tasks?
- The `ExecutorService` provides a method called `shutdownNow()`, which attempts to stop all running tasks and discards any that have not been started yet. Note that `shutdownNow()` attempts to stop all running tasks.
- `shutdownNow()` returns a `List<Runnable>` of tasks that were submitted to the thread executor but that were never started.

### Submitting Tasks

submit tasks to an `ExecutorService` instance multiple ways:
 - `execute()`, is inherited from the `Executor` interface, which the `ExecutorService` interface extends.
 - Java added `submit()` methods to the `ExecutorService` interface, which, like `execute()`, can be used to complete tasks asynchronously.
- Unlike `execute()`, though, `submit()` returns a `Future` object that can be used to determine if the task is complete. 

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-04%20at%209.16.52%20PM.png)

![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-04%20at%209.16.57%20PM.png)

### submitting Tasks: `execute()` vs `submit()`
`execute()` does not support `Callable` expressions, we tend to prefer `submit()` over `execute()`, even if you don't store the `Future` reference.

### Submitting Task Collections

* The `invokeAll()` method executes all tasks in a provided collection and returns a `List` of ordered `Future` objects, with one `Future` object corresponding to each submitted task, in the order they were in the original collection.

* The `invokeAny()` method executes a collection of tasks and returns the result of one of the tasks that successfully completes execution, cancelling all unfinished tasks.

The `ExecutorService` interface also includes overloaded versions of `invokeAll()` and `invokeAny()` that take a `timeout` value and `TimeUnit` parameter.

### Waiting for Results

the `submit()` method returns a `java.util.concurrent.Future<V> object`, or `Future<V>` for short.

```java
Future<?> future = service.submit(() -> System.out.println("Hello Zoo"));
```
![alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-04%20at%209.26.21%20PM.png)

Introducing `Callable`
---

`Callable` for short, which is similar to `Runnable` except that its `call()` method returns a value and can throw a checked exception.
 
### Ambiguous lambda expressions: Callable vs. Supplier

`Supplier` functional interface, in that they both take no arguments and return a generic type. One difference is that the method in `Callable` can `throw` a checked Exception.
- the `get()` methods on a Future object return the matching generic type or null

```java
public static void use(Callable<Integer> expression) {}
use(() -> {throw new IOException();}); // DOES NOT COMPILE
use((Callable<Integer>)() -> {throw new IOException("");}); // COMPILES
```

- Since `Callable` supports a return type when used with `ExecutorService`, it is often preferred over `Runnable` when using the Concurrency API

### Waiting for All Tasks to Finish
*  we use the `awaitTermination(long timeout, TimeUnit unit)` method available for all thread.
* The method waits the specified time to complete all tasks, returning sooner if all tasks finish or an `InterruptedException` is detected

```java
service.awaitTermination(1, TimeUnit.MINUTES);
```

### Scheduling Tasks

* The `ScheduledExecutorService`, which is a subinterface of `ExecutorService`, can be used for just such a task.
```java
ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
```
![alt text ](https://github.com/frhan/ocp/blob/master/images/7_1.png)

```java
ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
Runnable task1 = () -> System.out.println("Hello Zoo"); 
Callable<String> task2 = () -> "Monkey";
Future<?> result1 = service.schedule(task1, 10, TimeUnit.SECONDS); 
Future<?> result2 = service.schedule(task2, 8, TimeUnit.MINUTES);
```

The `scheduleAtFixedRate()` method creates a new task and submits it to the executor every period, regardless of whether or not the previous task finished. 
The following example executes a Runnable task every minute, following an initial five-minute delay:

`service.scheduleAtFixedRate(command,5,1,TimeUnit.MINUTE);`

On the other hand, the `scheduleAtFixedDelay()` method creates a new task after the previous task has finished.
For example, if the first task runs at 12:00 and takes five min- utes to finish, with a period of 2 minutes, then the second task will start at 12:07.

`service.scheduleAtFixedDelay(command,0,2,TimeUnit.MINUTE);`

### Increasing Concurrency with Pools
A _thread pool_ is a group of pre-instantiated reusable threads that are available to perform a set of arbitrary tasks.

![alt text ](https://github.com/frhan/ocp/blob/master/images/7_2.png)

The `newCachedThreadPool()` method will create a thread pool of unbounded size, allocating a new thread anytime one is required or all existing threads are busy. 

The `newFixedThreadPool()` takes a number of threads and allocates them all upon creation.

The `newScheduledThreadPool()` is identical to the `newFixedThreadPool()` method, except that it returns an instance of 
`ScheduledExecutorService` and is therefore compatible with scheduling tasks.
