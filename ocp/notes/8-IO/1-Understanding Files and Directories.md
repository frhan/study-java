# Understanding Files and Directories
## Conceptualizing the File System
 A _file_ is record within a file system that stores user and system data. Files are organized using directories.
 
 A _directory_ is a record within a file system that contains files as well as other directories.

 A _path_ is a String representation of a file or directory within a file system. 

## Introducing the File Class
The **`File`** class is used to read information about existing files and directories, list the contents of a directory, and create/delete files and directories.

* One common mistake new Java developers make is forgetting that the `File` class can be used to represent directories as well as files

## Creating a File Object
The _absolute path_ of a file or directory is the full path from the root directory to the file or directory, including all subdirectories that contain the file or directory

The _relative path_ of a file or directory is the path from the current working directory to file or directory.
For example, the following is an absolute path to the `zoo.txt` file.

* Unix-based systems use the forward slash `/` for paths, whereas Windows-based systems use the backslash `\` character.
* a system property and a static variable defined in the File class.

```java
System.out.println(System.getProperty("file.separator")); 
System.out.println(java.io.File.separator)
```
* A `File` object may refer to a path that does not exists within the file system
 
## Working with a File Object

![Alt text](https://github.com/frhan/study/blob/master/images/Screen%20Shot%202019-02-19%20at%202.55.54%20PM.png)
