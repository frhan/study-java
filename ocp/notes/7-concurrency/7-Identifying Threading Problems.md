# Identifying Threading Problems

## Understanding Liveness

## Deadlock
_Deadlock_ occurs when two or more threads are blocked forever, each waiting on the other.
We can illustrate this principle with the following example.

## Starvation
_Starvation_ occurs when a single thread is perpetually denied access to a shared resource
or lock. The thread is still active, but it is unable to complete its work as a result of other
threads constantly taking the resource that they trying to access.

## Livelock
_Livelock_ occurs when two or more threads are conceptually blocked forever, although they
are each still active and trying to complete their task. Livelock is a special case of resource
starvation in which two or more threads actively try to acquire a set of locks, are unable to
do so, and restart part of the process.

## Managing Race Conditions
_A race condition_ is an undesirable result that occurs when two tasks, which should be
completed sequentially, are completed at the same time