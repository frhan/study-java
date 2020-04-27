# Working with **Streams**
### The FileInputStream and FileOutputStream Classes
 * `FileInputStream` and `FileOutputStream`-  They are used to read bytes from a file or write bytes to a file, respectively. These classes include constructors that take a `File` object or `String`, representing a path to the file.

* The data in a `FileInputStream` object is commonly accessed by successive calls to the `read()` method until a value of `-1` is returned, indicating that the end of the stream—in this case the end of the file—has been reached.

* A `FileOutputStream` object is accessed by writing successive bytes using the `write(int)` method.

```java
import java.io.*;
public class CopyFileSample {
public static void copy(File source, File destination) throws IOException {
    try (InputStream in = new FileInputStream(source); 
        OutputStream out = new FileOutputStream(destination)) { 
            int b;
            while((b = in.read()) != -1) {
                out.write(b); 
            }
    } 
}
public static void main(String[] args) throws IOException { 
    File source = new File("Zoo.class");
    File destination = new File("ZooCopy.class"); copy(source,destination);
} 
}
```
* If the destination file already exists, it will be overridden by this code.
### The _BufferedInputStream_ and _BufferedOutputStream_ Classes
* Instead of reading the data one byte at a time, we use the underlying `read(byte[])` method of `BufferedInputStream`, which returns the number of bytes read into the provided byte array.
* The data is written into the `BufferedOutputStream` using the `write(byte[],int,int)` method, which takes as input a byte array, an offset, and a length value, respectively. 
    
    - The offset value is the number of values to skip before writing characters, and it is often set to 0. The length value is the number of characters from the byte array to write.

```java
import java.io.*;

public class CopyBufferFileSample {
    public static void copy(File source, File destination) throws IOException {
        try (
        InputStream in = new BufferedInputStream(new FileInputStream(source)); 
        OutputStream out = new BufferedOutputStream(new FileOutputStream(destinatio)){
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer,0,lengthRead);
                out.flush(); }
        } 
    }
}
```
#### why use the buffered Classes?
- The `Buffered` classes contain numerous performance enhancements for managing stream data in memory.

#### buffer size Tuning
It is also common to choose a power of 2 for the buffer size, since most underlying hardware is structured with file block and cache sizes that are a power of 2.

### The FileReader and FileWriter classes

- Like the `FileInputStream` and `FileOutputStream` classes, the FileReader and FileWriter classes contain read() and write() methods, respectively. 

These methods read/write char values instead of byte values; although similar to what you saw with streams, the API actually uses an int value to hold the data so that -1 can be returned if the end of the file is detected.
### The BufferedReader and BufferedWriter Classes
- Unlike `FileInputStream` and `FileReader`, where we used -1 to check for file termination of an int value, with `BufferedReader`, we stop reading the file when `readLine()` returns `null`.

### The `ObjectInputStream` and `ObjectOutputStream` Classes
- The process of converting an in-memory object to a stored data format is referred to as _serialization_, with the reciprocal process of converting stored data into an object, which is known as _deserialization_.

#### The Serializable Interface
 - The Serializable interface is a tagging or marker interface, which means that it does not have any methods associated with it.

- The purpose of implementing the `Serializable` interface is to inform any process attempting to serialize the object that you have taken the proper steps to make the object serializable, which involves making sure that the classes of all instance variables within the object are also marked `Serializable`. dd

- an object will throw a `NotSerializableException` if the class or one of its contained classes does not properly implement the `Serializable` interface. 

- `transient` keyword on the reference to the object, which will instruct the process serializing the object to skip it and avoid throwing a `NotSerializableException`.

- `static` class members will also be ignored during the serialization and deserialization process.

- `serialVersionUID` 
    - certainly not required as part of implementing the `Serializable` interface
    - is stored with the serialized object and assists during the deserialization process. The serialization process uses the `serialVersionUID` to identify uniquely a version of the class
    
#### `Serializing` and `Deserializing` Objects
- The `ObjectOutputStream` class includes a method to serialize the object to the stream called `void writeObject(Object)`
    - we might get a `ClassCastException` at runtime
    - If the provided object is not `Serializable`, or it contains an embedded reference to a class that is not `Serializable` or not marked `transient`, a `NotSerializableException` will be thrown at runtime.
-  the `ObjectInputStream` class includes a deserialization method that returns an object called `readObject()`.
    -  the `readObject()` throws the checked exception, `ClassNotFoundException`,since the class of the deserialized object may not be available to the JRE

```java
try (ObjectOutputStream out = new ObjectOutputStream(
new BufferedOutputStream(new FileOutputStream(dataFile)))) {
    for(Animal animal: animals) 
        out.writeObject(animal);
}
```

```java
try (ObjectInputStream in = new ObjectInputStream(
new BufferedInputStream(new FileInputStream(dataFile)))) { 
    while(true) {
        Object object = in.readObject(); if(object instanceof Animal)
        animals.add((Animal)object); 
    }
}
```

### Understanding Object Creation
 - When you deserialize an object, the constructor of the serialized class is not called.
- Java calls the first no-arg constructor for the first nonserializable parent class, skipping the constructors of any serialized class in between. Furthermore, any static variables or default initializations are ignored.

### The `PrintStream` and `PrintWriter` Classes

The `PrintStream` and `PrintWriter` classes are high-level stream classes that write formatted representation of Java objects to a text-based output stream.

**`print()`**

**`println()`**

**`format()` and `printf()`**

### Review of Stream Classes 

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-03-06%20at%2010.45.15%20PM.png)
