package com.nextstep.multiauhtnticate.configuration;
public class SingltonClassInJava {
    private static SingltonClassInJava instane;

    //private will not let contruct the ocject of class

    private SingltonClassInJava(){

    }

    //syncronized make accessible to only one thread

    // wihtout syncronized it is not properly syncronized//
    public static synchronized   SingltonClassInJava getInstance(){
        if(instane==null){
            instane=new SingltonClassInJava();
        }
        return instane;
    }
//
//    In the given SingletonClassInJava, the Singleton pattern ensures only one instance of the class is created and shared throughout the application:
//
//    Private Constructor: Prevents creating an object of the class directly using new, enforcing controlled instantiation.
//    Static Instance Variable: Holds the single instance of the class, initially set to null.
//    Lazy Initialization: The getInstance method checks if the instance is null. If it is, a new object is created; otherwise, the existing instance is returned.
//    Access Control: The static getInstance method is the only way to access the single instance.
//    Object Creation in Other Classes: Direct object creation using new is not possible due to the private constructor, but the getInstance method can be used to access the single instance.
//    Example of usage in another class:
//
//    java
//    Copy code
//    public class TestSingleton {
//        public static void main(String[] args) {
//            SingltonClassInJava singleton = SingltonClassInJava.getInstance(); // Access the instance
//        }
//    }


}
