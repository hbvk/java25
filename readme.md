[![Build Status](https://dev.azure.com/hbvk/java-test/_apis/build/status%2Fhbvk.java25?branchName=main)](https://dev.azure.com/hbvk/java-test/_build/latest?definitionId=44&branchName=main)

| Release | EOL     | Class File Version |
|---------|---------|--------------------|
| 2025-09 | 2030-09 | 69.0               |

# New Features

| JEP                                      | Feature                                        | introduced | test                                                                                |
|------------------------------------------|------------------------------------------------|------------|-------------------------------------------------------------------------------------|
| [454](https://openjdk.java.net/jeps/454) | Foreign Function & Memory API                  | 22         |                                                                                     |
| [456](https://openjdk.java.net/jeps/456) | Unnamed Variables & Patterns                   | 22         |                                                                                     |
| [458](https://openjdk.java.net/jeps/458) | Launch Multi-File Source-Code Programs         | 22         |                                                                                     |
| [467](https://openjdk.java.net/jeps/467) | Markdown Documentation Comments                | 23         |                                                                                     |
| [484](https://openjdk.java.net/jeps/484) | Class-File API                                 | 24         |                                                                                     |
| [485](https://openjdk.java.net/jeps/485) | Stream Gatherers                               | 24         |                                                                                     |
| [506](https://openjdk.java.net/jeps/506) | Scoped Values                                  | 25         |                                                                                     |
| [511](https://openjdk.java.net/jeps/511) | Module Import Declarations                     | 25         |                                                                                     |
| [512](https://openjdk.java.net/jeps/512) | Compact Source Files and Instance Main Methods | 25         |                                                                                     |
| [513](https://openjdk.java.net/jeps/513) | Flexible Constructor Bodies                    | 25         | [test case](src/test/java/com/hbvk/jep513/Jep513FlexibleConstructorBodiesTest.java) |

## Flexible Constructor Bodies

Flexible constructor bodies ([JEP513](https://openjdk.java.net/jeps/513)) were first introduced as a preview feature in
Java 22, under a different name, and finalized in Java 25.

In the body of a constructor, you can now have statements before `this()` or `super()`, e.g. initializations or
validations. These statements cannot reference the object under construction, of course. We can now replace the somewhat
convoluted `super(validate(x));` constructs by the more readable `validate(x); super(x);`. A useful addition, in my
opinion.