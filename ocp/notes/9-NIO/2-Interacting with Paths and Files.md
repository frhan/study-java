### Interacting with Paths and Files

#### **Path Object vs. actual file** 
- a Path object is not a file but a representation of a location within the file system. 
- retrieving the parent or root directory of a Path object does not require the file to exist, although the JVM may access the underlying file system to know how to process the path information.

### Providing Optional Arguments
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-07%20at%2012.30.07%20AM.png)

- An _atomic_ operation is any operation that is performed as a single indivisible unit of execution, which appears to the rest of the system as occurring instantaneously.
### Using Path Objects
- Many of the methods in the Path interface transform the path value in some way and return a `new Path` object, allowing the methods to be chained.

```java
Paths.get("/zoo/../home").getParent().normalize().toAbsolutePath();
```
### Viewing the Path with `toString()`, `getNameCount()`, and `getName()`
- `getNameCount()` and `getName(int)`, are often used in conjunction to retrieve the number of elements in the path and a reference to each element, respectively.
- If the Path object represents the root element itself, then the number of names in the Path object returned by `getNameCount()` will be 0.

### Accessing Path Components with `getFileName()`, `getParent()`, and `getRoot()`
- `getFileName()`, returns a `Path` instance representing the filename, which is the farthest element from the root.
- `getParent()`, returns a `Path` instance representing the parent path or `null` if there is no such parent.

### Checking Path Type with `isAbsolute()` and `toAbsolutePath()`
- `isAbsolute()`, returns `true` if the path the object references is absolute and `false` if the path the object references is relative.
- `toAbsolutePath()`, converts a relative Path object to an absolute Path object by joining it to the current working directory.

```java
Path path1 = Paths.get("C:\\birds\\egret.txt"); 
System.out.println("Path1 is Absolute? "+path1.isAbsolute());
System.out.println("Absolute Path1: "+path1.toAbsolutePath());
```
- if the Path object already represents an absolute path, then the output is a new Path object with the same value.

### Creating a New Path with `subpath()`
- `subpath(int,int)` returns a relative subpath of the `Path` object, referenced by an inclusive start index and an exclusive end index.

```java
Path path = Paths.get("/mammal/carnivore/raccoon.image"); 
System.out.println("Path is: "+path);
System.out.println("Subpath from 0 to 3 is: "+path.subpath(0,3)); 
System.out.println("Subpath from 1 to 3 is: "+path.subpath(1,3)); 
System.out.println("Subpath from 1 to 2 is: "+path.subpath(1,2));
```
```
Path is: /mammal/carnivore/raccoon.image
Subpath from 0 to 3 is: mammal/carnivore/raccoon.image
Subpath from 1 to 3 is: carnivore/raccoon.image
Subpath from 1 to 2 is: carnivore
```
- 0-indexed element is mammal in this example and not the root directory; therefore, the maximum index that can be used is 3
```java
System.out.println("Subpath from 0 to 4 is: "+path.subpath(0,4)); // THROWS //EXCEPTION AT RUNTIME
System.out.println("Subpath from 1 to 1 is: "+path.subpath(1,1)); // THROWS //EXCEPTION AT RUNTIME
```
### Using Path Symbols
![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-09%20at%2011.01.08%20PM.png)
- the path value `../bear.txt` refers to a file named `bear.txt` in the parent of the current directory
- the path value `./penguin.txt` refers to a file named `penguin.txt` in the current directory
- `../../lion.data` refers to a file `lion.data` that is two directories up from the current working directory.

### Deriving a Path with `relativize()`
- `relativize(Path)` for constructing the relative path from one Path object to another

```java
Path path1 = Paths.get("fish.txt");
Path path2 = Paths.get("birds.txt"); 
System.out.println(path1.relativize(path2)); 
System.out.println(path2.relativize(path1));
```
- The `relativize()` method requires that both paths be absolute or both relative, and it will throw an `IllegalArgumentException` if a relative path value is mixed with an absolute path value.

```java
Path path1 = Paths.get("/primate/chimpanzee");
Path path2 = Paths.get("bananas.txt"); 
Path1.relativize(path3); // THROWS EXCEPTION AT RUNTIME
```
### Joining Path Objects with `resolve()`
- a `resolve(Path)` method for creating a new `Path` by joining an existing path to the current path.

```java
final Path path1 = Paths.get("/cats/../panther"); 
final Path path2 = Paths.get("food"); 
System.out.println(path1.resolve(path2));
```
### Cleaning Up a Path with `normalize()`
- The `normalize()` method of the Path interface can normalize a path. 
- *Normalizing means* that it removes all the `.` and `..` codes in the middle of the path string, and resolves what path the path string refers to
### Checking for File Existence with `toRealPath()`
- The `toRealPath(Path)` method takes a `Path` object that may or may not point to an existing file within the file system, and it returns a reference to a real path within the file system.
- it also verifies that the file referenced by the path actually exists, and thus it throws a checked `IOException` at runtime if the file cannot be located. 
- The toRealPath() method performs additional steps, such as removing redundant path elements

## Interacting with Files

### Testing a Path with `exists()`
- The `Files.exists(Path)` method takes a `Path` object and returns `true` if, and only if, it references a file that exists in the file system.

### Testing Uniqueness with `isSameFile()`
- The `Files.isSameFile(Path,Path)` method is useful for determining if two Path objects relate to the same file within the file system. 
- It takes two Path objects as input and follows symbolic links.
- This `isSameFile()` method does not compare the contents of the file

### Making Directories with `createDirectory()` and `createDirectories()`
- `Files.createDirectory(Path)` method to create a directory. 
- `createDirectories()`, which like `mkdirs()` creates the target directory along with any nonexistent parent directories leading up to the target directory in the path.
- The directory creation methods can throw the checked `IOException`, such as when the directory cannot be created or already exists.

```java
try {
    Files.createDirectory(Paths.get("/bison/field"));
    Files.createDirectories(Paths.get("/bison/field/pasture/green"));
} catch (IOException e) {
// Handle file I/O exception...
}
```

### Duplicating File Contents with `copy()`
- `Files.copy(Path,Path)`, which copies a file or directory from one location to another. The `copy()` method throws the checked `IOException`, such as when the file or directory does not exist or cannot be read.
- Directory copies are shallow rather than deep, meaning that files and subdirectories within the directory are not copied.
- copying files and directories will traverse symbolic links, although it
will not overwrite a file or directory if it already exists, nor will it copy file attributes.

```java
try {
    Files.copy(Paths.get("/panda"), Paths.get("/panda-save"));
    Files.copy(Paths.get("/panda/bamboo.txt"), Paths.get("/panda-save/bamboo.txt"));
} catch (IOException e) {
// Handle file I/O exception...
}
```

### Changing a File Location with `move()`
The `Files.move(Path,Path)` method moves or renames a file or directory within the file system

```java
try {
    Files.move(Paths.get("c:\\zoo"), Paths.get("c:\\zoo-new"));
    Files.move(Paths.get("c:\\user\\addresses.txt"),
                 Paths.get("c:\\zoo-new\\addresses.txt"));
} catch (IOException e) {
// Handle file I/O exception...
}
```
- the `move()` method will follow links, throw an exception if the file already exists, and not perform an atomic move.

### Removing a File with `delete()` and `deleteIfExists()`
- The `Files.delete(Path)` method deletes a file or empty directory within the file system.
- The `delete()` method throws the checked `IOException` under a variety of circumstances. 
    - if the path represents a non-empty directory, the operation will throw the runtime `DirectoryNotEmptyException`.
    - If the target of the path is a symbol link, then the symbolic link will be deleted, not the target of the link.

- The `deleteIfExists(Path)` method is identical to the `delete(Path)` method, except that it will not throw an exception if the file or directory does not exist, but instead it will return a `boolean` value of `false`.

### Reading and Writing File Data with `newBufferedReader()` and `newBufferedWriter()`
- `Files.newBufferedReader(Path,Charset)`, reads the file specified at the `Path` location using a `java.io.BufferedReader` object

```java
try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("US-ASCII"))){

}
```

```java
try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-16")) ){
}
```
### Reading Files with readAllLines()
- The `Files.readAllLines()` method reads all of the lines of a text file and returns the results as an ordered `List` of `String` values. 
```java
Path path = Paths.get("/fish/sharks.log");

try {
    final List<String> lines = Files.readAllLines(path);
    for(String line: lines) { 
        System.out.println(line);
    }
} catch (IOException e) {
// Handle file I/O exception... }
```
