## Understanding File Attributes
- the Files class also provides numerous methods accessing file and directory metadata, referred to as file _attributes_. Put simply, metadata is data that describes other data. In this context, file metadata is data about the file or directory record within the file system and not the contents of the file.

#### Discovering Basic File Attributes

#### Reading Common Attributes with isDirectory(), isRegularFile(),and isSymbolicLink()

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-07%20at%2011.33.03%20PM.png)

### Checking File Visibility with `isHidden()`
-  In Linux- or Mac-based systems, this is often denoted by file or directory entries that begin with a period character.
- The `isHidden()` method throws the checked `IOException`, as there may be an I/O error reading the underlying file information.

### Testing File Accessibility with  `isReadable()` and `isExecutable()`
- This is important in file systems where the filename can be viewed within a directory, but the user may not have permission to read the contents of the file or execute it.

```java
System.out.println(Files.isReadable(Paths.get("/seal/baby.png")));
```
### Reading File Length with `size()`
- `Files.size(Path)` method is used to determine the size of the file in bytes.
- The `size()` method throws the checked `IOException` if the file does not exist or if the process is unable to read the file information.

### Managing File Modifications with `getLastModifiedTime()` and `setLastModifiedTime()`
- The Files class provides the method `Files.getLastModifiedTime(Path)`, which returns a `FileTime` object to accomplish this.
- The Files class also provides a mechanism for updating the last-modified date/time of a file using the `Files.setLastModifiedTime(Path,FileTime)` method
- Both of these methods have the ability to throw a checked `IOException` when the file is accessed or modified.

```java
try {
    final Path path = Paths.get("/rabbit/food.jpg"); 
    System.out.println(Files.getLastModifiedTime(path).toMillis());
    Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
    System.out.println(Files.getLastModifiedTime(path).toMillis());
} catch (IOException e) {
// Handle file I/O exception...
}
```

### Managing Ownership with _`getOwner()`_ and _`setOwner()`_
- the `Files.getOwner(Path)` method returns an instance of `UserPrincipal` that represents the owner of the file within the file system.

```java
UserPrincipal owner = FileSystems.getDefault()
                                .getUserPrincipalLookupService() 
                                .lookupPrincipalByName("jane");
```

### Improving Access with Views
-  A _view_ is a group of related attributes for a particular file system type. A file may support multiple views, allowing you to retrieve and update various sets of information about the file.
- more attributes are read than in a single method call, there are fewer roundtrips between Java and the operating system, whereas reading the same attributes with the previously described single method calls would require many such trips.

### Understanding Views
- `Files.readAttributes()`, returns a read-only view of the file attributes
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-07%20at%2011.49.21%20PM.png)
### Reading Attributes
### _BasicFileAttributes_
 - All attributes classes extend from `BasicFileAttributes`; therefore it contains attributes common to all supported file systems.

```java
BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);
```
- The `isOther()` method is used to check for paths that are not files, directories, or symbolic links, such as paths that refer to resources or devices in some file systems. 
- The `lastAccessTime()` and `creationTime()` methods return other date/time information about the file.
- The `fileKey()` method returns a file system value that represents a unique identifier for the file within the file system or null if it is not supported by the file system.

### Modifying Attributes
- `Files.readAttributes()` method is useful for reading file data, it does not provide a direct mechanism for modifying file attributes. 

### _BasicFileAttributeView_
- cannot modify the other basic attributes directly, since this would change the property of the file system object
```java

BasicFileAttributeView view = Files.getFileAttributeView(path,BasicFileAttributeView.class);

BasicFileAttributes data = view.readAttributes();

FileTime lastModifiedTime = FileTime.fromMillis( data.lastModifiedTime().toMillis()+10_000);

view.setTimes(lastModifiedTime,null,null);
```
