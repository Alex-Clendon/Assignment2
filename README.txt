NOTE:
To run the program, it is recommended that you run "Main" class in netbeans / IDE.
An executable JAR file can be created if the project is built and will appear in dist/, but running it will make an embedded
database inside of the dist/ folder rather than the root project folder.

Test cases for program will only work on a database folder than is in the root directory, not dist/,
and will create a database folder in the root directory if one isn't present.
Therefore, if you want to use the test cases for the program with a database that you have modified,
(e.g. new users added) You should run the program from "Main" in netbeans.
(If you do both, there will be 2 seperate instances of the "MillionareDB" database, which might cause confusion).

