#Interacting with Users
## The Old Way

## The New Way
- the `Console` class is a singleton
- It is created automatically for you by the JVM and accessed by calling the `System.console()` method. 
- this method will return `null` in environments where text interactions are not supported.

```java
    Console console = System.console(); 
    if(console != null) {
        String userInput = console.readLine();
        console.writer().println ("You entered the following: "+userInput); 
    }
```

#### `reader()` and `writer()`
- provides access to an instance of `Reader` and `PrintWriter` using the
methods `reader()` and `writer()`, respectively.

### `format() and printf()`
- The `format()` method takes a `String` format and list of arguments, and it behaves in the exact same manner as `String`;

```java
Console console = System.console(); 
console.writer().format(new Locale("fr", "CA"),"Hello World");
```

### `flush()`
- forces any buffered output to be written immediately.
### `readLine()`
- retrieves a single line of text from the user, and the user presses the Enter key to terminate it.

### `readPassword()`
 -  user does not see the text they are typing.
 - 