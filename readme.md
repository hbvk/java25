[![Build Status](https://dev.azure.com/hbvk/java-test/_apis/build/status%2Fhbvk.java25?branchName=main)](https://dev.azure.com/hbvk/java-test/_build/latest?definitionId=44&branchName=main)

| Release | EOL     | Class File Version |
|---------|---------|--------------------|
| 2025-09 | 2030-09 | 69.0               |

# New Features

| JEP                                      | Feature                                        | introduced | test                                                                                                                                                         |
|------------------------------------------|------------------------------------------------|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [454](https://openjdk.java.net/jeps/454) | Foreign Function & Memory API                  | 22         |                                                                                                                                                              |
| [456](https://openjdk.java.net/jeps/456) | Unnamed Variables & Patterns                   | 22         |                                                                                                                                                              |
| [458](https://openjdk.java.net/jeps/458) | Launch Multi-File Source-Code Programs         | 22         |                                                                                                                                                              |
| [467](https://openjdk.java.net/jeps/467) | Markdown Documentation Comments                | 23         |                                                                                                                                                              |
| [484](https://openjdk.java.net/jeps/484) | Class-File API                                 | 24         |                                                                                                                                                              |
| [485](https://openjdk.java.net/jeps/485) | Stream Gatherers                               | 24         |                                                                                                                                                              |
| [506](https://openjdk.java.net/jeps/506) | Scoped Values                                  | 25         |                                                                                                                                                              |
| [511](https://openjdk.java.net/jeps/511) | Module Import Declarations                     | 25         |                                                                                                                                                              |
| [512](https://openjdk.java.net/jeps/512) | Compact Source Files and Instance Main Methods | 25         | [example](src/main/java/com/hbvk/App.java)                                                                                                                   |
| [513](https://openjdk.java.net/jeps/513) | Flexible Constructor Bodies                    | 25         | [example](src/main/java/com/hbvk/jep513/FlexibleConstructorBodies.java), [test case](src/test/java/com/hbvk/jep513/Jep513FlexibleConstructorBodiesTest.java) |

## Compact Source Files and Instance Main Methods

[JEP512](https://openjdk.java.net/jeps/512) was first introduced in Java 21, as a preview feature, and finalized in
Java 25. It aims to make Java simpler for small programs, to help students new to Java to write simple programs in a
concise manner, and grow their code gracefully as their skills grow. It does so by removing clutter, e.g. from the main
method.

A simple Hello World program is traditionally written as:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

In Java 25 it can be simplified to:

```java
void main() {
    IO.println("Hello, World!");
}
```

We can run it directly from source code:

```bash
java HelloWorld.java
```

This feature introduces two enhancements:

- Instance Main Methods: `main()` methods don't have to be static or public anymore, and we can skip the arguments (
  `String[] args`) if we don't need them.
- Compact Source Files: No class declaration necessary, for small programs that consist of a few fields and methods. A
  source file with fields and methods that are not enclosed in a class declaration implicitly defines a class containing
  these fields and methods. The implicitly declared class of a compact source file is a final top-level class in the
  unnamed package, extends java.lang.Object, does not implement any interfaces, has a default constructor with no
  parameters, and no other constructors. It *must* have a launchable main method.

## Flexible Constructor Bodies

Flexible constructor bodies ([JEP513](https://openjdk.java.net/jeps/513)) were first introduced as a preview feature in
Java 22, under a different name, and finalized in Java 25.

In the body of a constructor, you can now have statements before `this()` or `super()`, e.g. initializations or
validations. These statements cannot reference the object under construction, of course. We can now replace the somewhat
convoluted `super(validate(x));` constructs by the more readable `validate(x); super(x);`. A useful addition, in my
opinion.