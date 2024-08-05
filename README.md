# Cron Expression Parser



Cron Expression Parser is a utility tool which reads a cron job expression and expands each field
to show the times at which it will run.

The script was developed in java and gradle was used for building and unit testing.
The decision to use these tools was taken given the popularity of the tools in java world.


### Building the script
## Prerequisites
Gradle 7.x

JDK 1.8

From the root of the project, Run `gradlew clean build` :
```bash
$ gradlew clean build
..
[INFO] BUILD SUCCESS
..
```

### Running the script
To directly run the script, Download v1.0 release from here https://github.com/sai17386/cron-expression-parser/releases and run below command:

```bash
$ java -jar cron-expression-parser-1.0-SNAPSHOT.jar "0 0 1,2,3,15 * 1-5 /usr/bin/find"
  minute        0
  hour          0
  day of month  1 2 3 15
  month         1 2 3 4 5 6 7 8 9 10 11 12
  day of week   1 2 3 4 5
  command       /usr/bin/find
```