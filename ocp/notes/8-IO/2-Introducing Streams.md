# Introducing Streams

## Stream Fundamentals
The contents of a file may be accessed or written via a _stream_, which is a list of data elements presented sequentially.

## Stream Nomenclature
### Byte Streams vs. Character Streams
The `java.io` API defines two sets of classes for reading and writing streams: those with Stream in their name and those with `Reader`/`Writer` in their name.

Differences between Streams and `Readers`/`Writers`:
1. The stream classes are used for inputting and outputting all types of binary or byte data.
2. The reader and writer classes are used for inputting and outputting only character and String data.

why use Character streams?
- There are advantages, though, to using the reader/writer classes, as they are specifically focused on managing character and string data
- the character stream classes are sometimes referred to as convenience classes for working with text data.

## Input and Output
- Most Input stream classes have a corresponding Output class and vice versa. For example, the `FileOutputStream` class writes data that can be read by a `FileInputStream`.

## Low-Level vs. High-Level Streams

- A low-level stream connects directly with the source of the data, such as a file, an array, or a String.
For example, a `FileInputStream` is a class that reads file data one byte at a time.
- a high-level stream is built on top of another stream using wrapping. Wrapping is the process by which an instance is passed to the constructor of another class and operations on the resulting instance are filtered and applied to the original instance.

```java
try (
BufferedReader bufferedReader = new BufferedReader(
new FileReader("zoo-data.txt"))) { 
    System.out.println(bufferedReader.readLine());
}
```
* High-level streams can take other high-level streams as input

```java
try ( ObjectInputStream objectStream = new ObjectInputStream( new BufferedInputStrem(new FileInputStream("zoo-data.txt")))) {
     System.out.println(objectStream.readObject());
}
```

### Stream Base Classes
The `java.io` library defines four abstract classes that are the parents of all stream classes defined within the API: 
`InputStream`, `OutputStream`, `Reader`, and `Writer`.

```java
new BufferedInputStream(new FileReader("zoo-data.txt")); // DOES NOT COMPILE 
new BufferedWriter(new FileOutputStream("zoo-data.txt")); // DOES NOT COMPILE 
new ObjectInputStream(new FileOutputStream("zoo-data.txt")); // DOES NOT COMPILE 
new BufferedInputStream(new InputStream()); // DOES NOT COMPILE
```

### Decoding Java I/O Class Names

Review of java.io Class Properties:
* A class with the word `InputStream` or `OutputStream` in its name is used for reading or writing binary data, respectively.

* A class with the word `Reader` or `Writer` in its name is used for reading or writing character or string data, respectively.

* Most, but not all, input classes have a corresponding output class.
* A low-level stream connects directly with the source of the data.
* A high-level stream is built on top of another stream using wrapping.
* A class with Buffered in its name reads or writes data in groups of bytes or characters and often improves performance in sequential file systems.

## Common Stream Operations
### Closing the Stream
* Since streams are considered resources, it is imperative that they be closed after they
are used lest they lead to resource leaks.
* In a file system, failing to close a file properly could leave it locked by the operating system such that no other processes could read/write to it until the program is terminated

### Flushing the Stream

* When data is written to an OutputStream, the underlying operating system does not necessarily guarantee that the data will make it to the file immediately. In many operating systems, the data may be cached in memory, with a write occurring only after a temporary cache is filled or after some amount of time has passed.

* Java provides a flush() method, which requests that all accumulated data be written immediately to disk.
The flush() method helps reduce the amount of data lost if the application terminates unexpectedly. It is not without cost, though.

### Marking the Stream
* The `InputStream` and `Reader` classes include `mark(int)` and `reset()` methods to move the stream back to an earlier position
* call the `markSupported()` method, which returns true only if `mark()` is supported.
* trying to call `mark(int)` or `reset()` on a class that does not support these operations will throw an exception at runtime
*  If at any point you want to go back to the earlier position where you last called `mark()`, then you just call `reset()` and the stream will “revert” to an earlier state.
* you should not call the mark() operation with too large a value as this could take up a lot of memory.

```java
InputStream is = ... 
System.out.print ((char)is.read()); 
if(is.markSupported()) {
    is.mark(100); 
    System.out.print((char)is.read()); 
    System.out.print((char)is.read()); 
    is.reset();
} 

System.out.print((char)is.read()); 
System.out.print((char)is.read()); 
System.out.print((char)is.read());
```
* Finally, if you call `reset()` after you have passed your `mark()` read limit, an exception may be thrown at runtime since the marked position may become invalidated.
### Skipping over Data
The `InputStream` and `Reader` classes also include a `skip(long)` method, which as you might expect skips over a certain number of bytes. It returns a `long` value, which indicates the number of `bytes` that were actually skipped.
