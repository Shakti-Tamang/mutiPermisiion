package com.nextstep.multiauhtnticate.configuration;

public class CheckedAndNotChecked {



//    RuntimeException is an  unchecked excpetion
//
//    In Java, exceptions are divided into two main categories: checked exceptions and unchecked exceptions. Understanding their differences helps in designing robust and maintainable applications.
//
//    Checked Exceptions
//    Definition: Checked exceptions are exceptions that the compiler enforces the programmer to handle, either using a try-catch block or by declaring them in the method signature with the throws keyword.
//            Purpose: They represent conditions that a reasonable program might want to recover from, such as file not found, invalid input, etc.
//            Examples:
//    IOException
//            SQLException
//    FileNotFoundException
//            ClassNotFoundException
//    Example:
//    java
//    Copy code
//import java.io.File;
//import java.io.FileReader;
//
//    public class CheckedExample {
//        public static void main(String[] args) {
//            try {
//                File file = new File("nonexistentfile.txt");
//                FileReader fr = new FileReader(file); // This might throw FileNotFoundException
//            } catch (Exception e) {
//                System.out.println("File not found: " + e.getMessage());
//            }
//        }
//    }
//    Key Points:
//    Must be handled at compile time.
//    If not handled, the program will not compile.
//            Unchecked Exceptions
//    Definition: Unchecked exceptions are exceptions that the compiler does not require you to handle or declare in the method signature. These usually result from programming bugs.
//    Purpose: They represent defects in the program, such as logical errors or improper use of APIs, which a program typically cannot recover from.
//    Examples:
//    NullPointerException
//            ArrayIndexOutOfBoundsException
//    ArithmeticException
//            IllegalArgumentException
//    Example:
//    java
//    Copy code
//    public class UncheckedExample {
//        public static void main(String[] args) {
//            int num = 10 / 0; // This will throw ArithmeticException
//        }
//    }
//    Key Points:
//    Checked only at runtime.
//    If not handled, the program terminates abruptly.
//    Key Differences
//    Aspect	Checked Exceptions	Unchecked Exceptions
//    Compile-time check	Yes	No
//    Declaration	Must be declared using throws or handled	Not mandatory to declare or handle
//    Examples	IOException, SQLException	NullPointerException, ArithmeticException
//    Use case	Recoverable situations	Programming bugs or logical errors
//    Best Practices
//    Use checked exceptions for scenarios that are recoverable and need attention from the caller, such as missing resources or connectivity issues.
//    Use unchecked exceptions for programming errors that should not occur in a properly designed program, such as NullPointerException.
//    Understanding when to use each type of exception ensures more predictable and manageable code.
}
