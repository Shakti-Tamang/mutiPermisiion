package com.nextstep.multiauhtnticate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class ComponentAndBean {
//
//    @Component
//    @Bean

//    both component and bean create beans

//     but componenet works on class level and bean works on method level

//    @componenet makes class as bean making it available for componenet scanning inside application
    //it will be detected at component scanning


//    @bean is used inside configuration class annoted by @Configuration
//    and it is used to create and register bean inside application context explicitly


//
//    @Component: Marks a class as a Spring-managed bean. It is automatically detected during component
//    scanning (like @Service or @Repository).
//    @Bean: Declares a method that produces a Spring-managed bean, typically used inside a
//    class annotated with @Configuration.
//            Difference: @Component works at the class level, while @Bean works at the method level.
//    Use of @Component: Automatically detects and registers the class as a bean in the application context.
//    Use of @Bean: Explicitly defines and customizes beans when automatic detection isn’t possible.
//    Example: Use @Component for general-purpose classes and @Bean for advanced
//    configurations in @Configuration classes.

//    @Component makes a class a Spring bean and registers it automatically for dependency injection.
//    @Bean allows you to define a bean with specific configurations and settings within a configuration class.
//
//    @Bean is used to explicitly define beans in Spring, giving you control over their
//        creation, configuration, and dependencies, which allows for more customized behavior
//    in your application.
}
