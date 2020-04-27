# Managing Concurrent Processes

## Creating a `CyclicBarrier`

A synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point. `CyclicBarriers` are useful in programs involving a fixed sized party of threads that must occasionally wait for each other. The barrier is called _cyclic_ because it can be re-used after the waiting threads are released.

The `CyclicBarrier` takes in its constructors a limit value, indicating the number of threads to wait for. As each thread finishes, it calls the `await()` method on the cyclic barrier. Once the specified number of threads have each called `await()`, the barrier is released and all threads can continue.

-  The `CyclicBarrier` class allows us to perform complex, multi-threaded tasks, while all threads stop and wait at logical barriers.

## Applying the Fork/Join Framework

## `Class ForkJoinTask<V>`
- run within a `ForkJoinPool`.
- `ForkJoinTasks` stems from a set of restrictions (that are only partially statically enforceable) reflecting their main use as computational tasks calculating pure functions or operating on purely isolated objects.
- The primary coordination mechanisms are `fork()`, that arranges asynchronous execution, 
    and `join()`, that doesn't proceed until the task's result has been computed.
-   Method `invoke()` is semantically equivalent to `fork()`; `join()` but always attempts to begin execution in the current thread. 
 
## `Class RecursiveTask<V> extends ForkJoinTask<V>`
 - A recursive result-bearing `ForkJoinTask`.
 
    `protected abstract V compute()`

## `public abstract class RecursiveAction extends ForkJoinTask<Void> `
- A recursive resultless `ForkJoinTask`. This class establishes conventions to parameterize resultless actions as `Void ForkJoinTasks`.

    `protected abstract void	compute()`

## `Interface BlockingDeque<E>`
A `Deque` that additionally supports blocking operations that wait for the deque to become non-empty when retrieving an element, and wait for space to become available in the deque when storing an element.

## `Interface ExecutorService`
- An `Executor` that provides methods to manage termination and methods that can produce a Future for tracking progress of one or more asynchronous tasks.

    `<T> Future<T>	submit(Callable<T> task)`

    `Future<?>	submit(Runnable task)`


## Package `java.util.concurrent.atomic`
- A small toolkit of classes that support lock-free thread-safe programming on single variables.
- 

## `Class CyclicBarrier`
- A synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point.
- CyclicBarriers are useful in programs involving a fixed sized party of threads that must occasionally wait for each other.
- The barrier is called cyclic because it can be re-used after the waiting threads are released.

    `CyclicBarrier(int parties)`
    
    `CyclicBarrier(int parties, Runnable barrierAction)`

    `int await()`
