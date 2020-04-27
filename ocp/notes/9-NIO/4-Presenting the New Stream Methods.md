## Presenting the New Stream Methods
### Conceptualizing Directory Walking
- Every record in a file system has exactly one parent, with the exception of the root directory, which sits atop everything
- A common task in a file system is to iterate over the descendants of a particular file path, either recording information about them or, more commonly, filtering them for a specific set of files
- _Walking or traversing_ a directory is the process by which you start with a parent directory and iterate over all of its descendants until some condition is met or there are no more elements over which to iterate

### Selecting a Search Strategy
- A _depth-first search_ traverses the structure from the root to an arbitrary leaf and then navigates back up toward the root, traversing fully down any paths it skipped along the way. The search depth is the distance from the root to current node.

- a _breadth-first search_ starts at the root and processes all elements of each particular depth, or distance from the root, before proceeding to the next depth level

### Walking a Directory
- The Files.walk(path) method returns a Stream<Path> object that traverses the directory in a depth-first, lazy manner.
- By lazy, we mean the set of elements is built and read while the directory is being traversed

```java
Path path = Paths.get("/bigcats");
try {
    Files.walk(path)
        .filter(p -> p.toString().endsWith(".java")) 
        .forEach(System.out::println);
} catch (IOException e) {
    // Handle file I/O exception...
}
```

### Avoiding Circular Paths
- A _cycle_ is an infinite circular depen- dency in which an entry in a directory is an ancestor of the directory. 

    For example, imagine that we had a directory `/birds/robin` that contains a symbolic link called `/birds/robin/allBirds` that pointed to `/birds`. Trying to traverse the `/birds/robin` directory would result in an infinite loop since each time the `allBirds` subdirectory was reached, we would go back to the parent path

### Searching a Directory
- `Files.find(Path,int,BiPredicate)` method behaves in a similar manner as the `Files.walk()` method, except that it requires the depth value to be explicitly set along with a `BiPredicate` to filter the data. 

```java
Path path = Paths.get("/bigcats"); long dateFilter = 1420070400000l;
try {
    Stream<Path> stream = Files.find(path, 10, 
                                    (p,a) -> p.toString().endsWit(".java") && a.lastModifiedTime().toMillis() > dateFilter);
                                    
    stream.forEach(System.out::println);
} catch (Exception e) {
// Handle file I/O exception...
}
```
### Listing Directory Contents
### Printing File Contents
- the NIO.2 API in Java 8 now includes a `Files.lines(Path)` method that returns a `Stream<String>` object and does not suffer from this same issue. The contents of the file are read and processed lazily, which means that only a small portion of the file is stored in memory at any given time.
```java
    Files.lines(path).forEach(System.out::println);
```

