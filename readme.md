[![Build Status](https://dev.azure.com/hbvk/java-test/_apis/build/status%2Fhbvk.java25?branchName=main)](https://dev.azure.com/hbvk/java-test/_build/latest?definitionId=44&branchName=main)

| Release | EOL     | Class File Version |
|---------|---------|--------------------|
| 2025-09 | 2030-09 | 69.0               |

# New Features

| JEP                                      | Feature                                        | introduced | test                                                                                                                                                         |
|------------------------------------------|------------------------------------------------|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [454](https://openjdk.java.net/jeps/454) | Foreign Function & Memory API                  | 22         |                                                                                                                                                              |
| [456](https://openjdk.java.net/jeps/456) | Unnamed Variables & Patterns                   | 22         | [test case](src/test/java/com/hbvk/jep456/Jep456AnonymousVariableTest.java)                                                                                  |
| [458](https://openjdk.java.net/jeps/458) | Launch Multi-File Source-Code Programs         | 22         |                                                                                                                                                              |
| [467](https://openjdk.java.net/jeps/467) | Markdown Documentation Comments                | 23         |                                                                                                                                                              |
| [484](https://openjdk.java.net/jeps/484) | Class-File API                                 | 24         |                                                                                                                                                              |
| [485](https://openjdk.java.net/jeps/485) | Stream Gatherers                               | 24         | [example](src/main/java/com/hbvk/jep485/DistinctByGatherer.java), [test case](src/test/java/com/hbvk/jep485/Jep485GathererTest.java)                         |
| [506](https://openjdk.java.net/jeps/506) | Scoped Values                                  | 25         |                                                                                                                                                              |
| [511](https://openjdk.java.net/jeps/511) | Module Import Declarations                     | 25         |                                                                                                                                                              |
| [512](https://openjdk.java.net/jeps/512) | Compact Source Files and Instance Main Methods | 25         | [example](src/main/java/com/hbvk/App.java)                                                                                                                   |
| [513](https://openjdk.java.net/jeps/513) | Flexible Constructor Bodies                    | 25         | [example](src/main/java/com/hbvk/jep513/FlexibleConstructorBodies.java), [test case](src/test/java/com/hbvk/jep513/Jep513FlexibleConstructorBodiesTest.java) |

## Unnamed Variables & Patterns

Previewed in Java 21, and finalized in Java 22, Unnamed Variables &
Patterns ([JEP456](https://openjdk.java.net/jeps/456)) are long overdue, I can't wait to replace some unnecessary
`ignored` variables in my projects. A method like this:

```java
int count(Iterable<MyObject> objects) {
    int total = 0;
    for (var object : objects) {
        // object is not used
        total++;
    }
    return total;
}
```

can now be written without reference to the unused as:

```java
int count(Iterable<MyObject> objects) {
    int total = 0;
    for (var _ : objects) {
        total++;
    }
    return total;
}
```

Other examples are lambdas, catch clauses, switches, try-with-resources and more. See
the [test case](src/test/java/com/hbvk/jep456/Jep456AnonymousVariableTest.java) for a few more examples.

## Stream Gatherers

Stream gatherers ([JEP485](https://openjdk.java.net/jeps/485)) were first introduced as a preview in Java 22, and
finalized in Java 24. Stream gatherers are an extension point for intermediate operations on streams, allowing
constructs like:

```java
Stream.of(1,2,3,4,5,6,7,8,9,0).

gather(Gatherers.scan(() ->"",(string,number)->string +number)).

toList(); // will contain: ["1", "12", "123", "1234", "12345", "123456", "1234567", "12345678", "123456789"]
```

`Gatherers.scan()` is a predefined gatherer, but it is also possible to define your own gatherers. I expect, though,
that the built-in ones are usually sufficient, and you will rarely need to write your own.

Stream gatherers are a good idea, but unfortunately the implementation lacks the ease of use of similar Kotlin
constructs.

## Module Import Declarations

[JEP511](https://openjdk.java.net/jeps/511) was first introduced in Java 22, as a preview feature, and finalized in
Java 25. It allows for the import of a whole module in one statement, e.g.:

```java
import module java.sql;
```

This has the same effect as

```java
import java.sql.*;
import javax.sql.*;
```

This is not a feature I'm planning to use often, I prefer smaller, more fine-grained imports.

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

Both enhancements can be useful for small quick-and-dirty programs, but I don't see any use for them in larger
codebases.