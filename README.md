# Classified

A simple, open source Java class file parser and explorer library, written in Java 8.

This project is highly a **work in-progress**, and is for research purposes only at this time. In the future, this project may be added to Maven. This project may also eventually grow into a full JVM in the far 
future.

Contributions are more than welcome, and as per LICENSE, you may use any part of this project for personal or 
commercial use with the proper attributions.

## Usage 

The usage of Classified stems around the ``ClassFile`` class, which represents a class file along with its attributes and data. You can obtain a ``ClassFile`` instance using one of three static factory methods:
  * ``ClassFile.fromDisk()``, which returns a new instance from either a full path to a ``.class`` file as a String, or a ``java.nio.file.Path`` object, likewise containing a path to class file.
  * ``ClassFile.fromBytes()``, which returns a new instance from a raw byte array which makes up a full ``.class`` file.
  * ``ClassFile.fromJVM()``, which returns a new instance from a fully-qualified class name of an existing class that is loaded in the current JVM.
  
Here is a basic example on creating a ``ClassFile`` object from ``Sample.class``, stored on a users Desktop.

```java
import club.hazsi.classified.classes.ClassFile;

public class Main {
    public static void main(String[] args) {
        try {
            ClassFile classFile = ClassFile.fromDisk("C:/Users/demo/Desktop/Sample.class");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

Instances of ``ClassFile`` contain a ``ClassAttributes`` object containing a full analysis of the class, with all individual components split, parsed and modifiable.

More documentation will be written in the future and placed in the wiki. Feel free to help contribute!
