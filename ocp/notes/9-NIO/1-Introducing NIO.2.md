## Introducing NIO.2
- the  `java.io` API uses byte streams to interact with file data
- The `NIO` API introduced the concepts of **buffers** and **channels** in place of `java.io` streams
- The basic idea is that you load the data from a file channel into a temporary buffer that, unlike byte streams, can be read forward and backward without blocking on the underlying resource.

### Introducing Path
- A `Path` object represents a hierarchical path on the storage system to a file or directory
- `Path` is a direct replacement for the legacy `java.io.File` class
- the `Path` interface contains support for symbolic links. A symbolic link is a special file within an operating system that serves as a reference or pointer to another file or directory.

### Creating Instances with Factory and Helper Classes
### **why Is Path an Interface?**
- The advantage of using the factory pattern here is that you can write the same code that will run on a variety of different platforms
![Alt test](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-10%20at%201.09.32%20AM.png)
### Creating Paths

```java
Path path1 = Paths.get("pandas/cuddly.png");
Path path3 = Paths.get("/home/zoodirector");
```
- a `Path` using the `Paths` class using a `vararg` of type `String`, such as `Paths.get(String,String...)`.
-  a `Path` using the `Paths` class is with a `URI` value.
- `new URI(String)` does throw a checked `URISyntaxException`, which would have to be caught in any application

```java
Path path1 = Paths.get(new URI("file://pandas/cuddly.png")); // THROWS EXCEPTION                                                                   //AT RUNTIME
Path path2 = Paths.get(new URI("file:///c:/zoo-info/November/employees.txt")); 
Path path3 = Paths.get(new URI("file:///home/zoodirectory"));
```

### absolute vs. relative Is file system dependent
- If a path starts with a forward slash, it is an absolute path, such as `/bird/parrot`. 
- If a path starts with a drive letter, it is an absolute path, such as `C:\bird\emu`. 
- Otherwise, it is a relative path, such as `..\eagle`


### Accessing the Underlying _`FileSystem`_ Object
- The `FileSystem` class has a protected constructor, so we use the plural `FileSystems` factory class to obtain an instance of `FileSystem`
 ```java
 Path path2 = FileSystems.getDefault().getPath("c:","zooinfo","November", "employees.txt");
 ```

### Using the Paths Class
