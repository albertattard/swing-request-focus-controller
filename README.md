# Swing - a replacement for `sun.awt.RequestFocusController`

A simple application that demonstrates alternative ways to request focus in
Swing applications.

## Prerequisite

- [Oracle Java 8](https://www.oracle.com/java/technologies/downloads/#java8)
- [Oracle Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven Toolchains](https://maven.apache.org/guides/mini/guide-using-toolchains.html)
  configured for the Java 8 and Java 17

## Building and running

1. Run the application using Maven

   ```shell
   $ ./mvnw clean compile exec:exec
   ```

2. Build the application using Maven

   ```shell
   $ ./mvnw clean package
   ```

   (_Optional_) Verify that the JAR file was created.

   ```shell
   $ tree './target/libs'
   ```

   ```
   ./target/libs
   └── swing-request-focus-controller-1.0.0.jar
   ```

3. Run the application using Maven

   ```shell
   $ java -jar ./target/libs/swing-request-focus-controller-1.0.0.jar
   ```
