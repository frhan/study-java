

Java Concurrency In Practice
==

Introduction
==
motivating factors led to the development of operating systems -
 * Resource utilization : Programs sometimes have to wait
 * Fairness : time slicing
 * Convenience : program coordination

-  Threads allow multiple streams of program control flow to coexist within a proc- ess.
- They share process-wide resources such as memory and file handles, but each thread has its own program counter, stack, and local variables.
- multiple threads within the same program can be scheduled simultaneously on multiple CPUs

Benefits of threads
--
 - improve the performance of complex applicationsÂ
 - reduce development and maintenance costs

Exploiting multiple processors
--
- When properly designed, multithreaded programs can improve throughput by utilizing available processor resources more effectively.

Risks of threads
--
* Safety hazards
* Liveness hazards
 - use of threads introduces additional forms of liveness failure that do not occur in single-threaded programs.
 - A liveness failure occurs when an activity gets into a state such that it is permanently unable to make forward progress
* Performance hazards
 - Context switches—when the scheduler suspends the active thread temporarily so another thread can run—are more frequent in applications with many threads, and have significant costs: saving and restoring execution context, loss of lo- cality, and CPU time spent scheduling threads instead of running them.

Threads are everywhere
--
- Every Java application uses threads.
-  When the JVM starts, it creates threads for JVM housekeeping tasks (garbage collection,finalization) and a main thread for running the main method.

Chapter 2 : Thread Safety
==
- Writing thread-safe code is, at its core, about managing access to state, and in particular to shared, mutable state.
- Whether an object needs to be thread-safe depends on whether it will be accessed from multiple threads.
- an object’s state is its data, stored in state variables such as instance or static fields.
- shared, we mean that a variable could be accessed by multiple threads;
- by mutable, we mean that its value could change during its lifetime.
- The primary mechanism for synchronization in Java is the *synchronized* keyword, which pro- vides exclusive locking, but the term “synchronization” also includes the use of volatile variables, explicit locks, and atomic variables.

here are three ways to fix broken programs:
* Don’t share the state variable across threads;
* Make the state variable immutable; or
* Use synchronization whenever accessing the state variable.

It is far easier to design a class to be thread-safe than to retrofit it for thread safety later.

the better encapsulated your program state, the easier it is to make your program thread-safe and to help maintainers keep it that way.

When designing thread-safe classes, good object-oriented techniques— encapsulation, immutability, and clear specification of invariants—are your best friends.

What is thread safety?
---
 - A class is thread-safe if it behaves correctly when accessed from multiple threads, regardless of the scheduling or interleaving of the execution of those threads by the runtime environment, and with no additional synchronization or other coordination on the part of the calling code.

 - Thread-safe classes encapsulate any needed synchronization so that clients need not provide their own.

 - Stateless objects are always thread-safe.


Atomicity
---
Race conditions
---
- A **race condition** occurs when the correctness of a computation depends on the relative timing or interleaving of multiple threads by the runtime; in other words, when getting the right answer relies on lucky timing

- **check-then-act** : you observe something to be true (file X doesn’t exist) and then take action based on that observation (create X); but in fact the observation could have become invalid between the time you observed it and the time you acted on it (someone else created X in the meantime), causing a problem (unexpected exception, overwritten data, file corruption).

**Example: race conditions in lazy initialization**
 ```java
@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;
    public ExpensiveObject getInstance() {
      if(instance == null)
        instance = new ExpensiveObject();
      return instance;
   }
}
 ```
 Say that threads A and B execute getInstance at the same time.

 Read-modify-write operations, like incrementing a counter, define a transformation of an object’s state in terms of its previous state.

 To increment a counter, you have to know its previous value and make sure no one else changes or uses that value while you are in mid-update.

 race conditions don’t always result in failure:

 Compound actions
 ---
*Operations A and B are atomic with respect to each other if, from the perspective of a thread executing A, when another thread executes B, either all of B has executed or none of it has. An atomic operation is one that is atomic with respect to all operations, including itself, that operate on the same state*

- **compound actions**: sequences of operations that must be executed atomically in order to remain thread-safe.

```java
@ThreadSafe
public class CountingFactorizer implements Servlet {
  private final AtomicLong count = new AtomicLong(0);
  public long getCount() { return count.get(); }
  public void service(ServletRequest req, ServletResponse resp) {
    BigInteger i = extractFromRequest(req);
    BigInteger[] factors = factor(i);
    count.incrementAndGet();
    encodeIntoResponse(resp, factors);
  }
}
```

The **java.util.concurrent.atomic** package contains atomic variable classes for effecting atomic state transitions on numbers and object references.

Locking
---

- To preserve state consistency, update related state variables in a single atomic operation.

  Intrinsic locks
  ---
  - Java provides a built-in locking mechanism for enforcing atomicity: the *synchronized block*.
  - A synchronized block has two parts: a reference to an object that will serve as the *lock*, and a block of code to be guarded by that lock.
  - A synchronized method is a shorthand for a synchronized block that spans an entire method body, and whose lock is the object on which the method is being invoked.

  ```java
  synchronized (lock) {
      // Access or modify shared state guarded by lock
  }

  ```
   - Every Java object can implicitly act as a lock for purposes of synchronization; these built-in locks are called intrinsic locks or monitor locks.
   - Intrinsic locks in Java act as mutexes (or mutual exclusion locks), which means that at most one thread may own the lock.

Reentrancy
---
 - intrinsic locks are reentrant, if a thread tries to acquire a lock that it already holds, the request succeeds. Reentrancy means that locks are acquired on a per-thread rather than per-invocation basis.
 - Reentrancy is implemented by associating with each lock an acquisition count and an owning thread.

Guarding state with locks
---
- For each mutable state variable that may be accessed by more than one thread, all accesses to that variable must be performed with the same lock held. In this case, we say that the variable is guarded by that lock.  
- The fact that every object has a built-in lock is just a convenience so that you needn’t explicitly create lock objects.
- ***Every shared, mutable variable should be guarded by exactly one lock. Make it clear to maintainers which lock that is.***


A common locking convention is to encapsulate all mutable state within an object and to protect it from concurrent access by synchronizing any code path that accesses mutable state using the object’s intrinsic lock.
