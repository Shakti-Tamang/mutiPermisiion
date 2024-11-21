package com.nextstep.multiauhtnticate.configuration;

public class ScopesINSpringBoot {
//    In Spring Boot, the default scope for a Spring bean is singleton.
//
//    What does Singleton Scope Mean?
//    Singleton is the default bean scope in Spring, meaning:
//    A single instance of the bean is created by the Spring container.
//    This instance is shared across the entire application for all requests for the bean.
//


//    In Spring Boot, scope defines the lifecycle and visibility of a bean in the application context. It determines how and when a bean instance is created, shared, and destroyed.
//
//    Common Scopes and Their Uses:
//    Singleton (Default):
//
//    A single instance of the bean is created and shared across the application.
//            Use: For stateless beans, e.g., service classes, where shared access is acceptable.
//    Prototype:
//
//    A new instance is created every time the bean is requested.
//            Use: For stateful beans or objects that need to have unique states for each request.
//    Request (Web only):
//
//    A new bean instance is created for every HTTP request.
//            Use: For request-scoped data, like HTTP headers or parameters.
//    Session (Web only):
//
//    A bean instance is created per HTTP session.
//    Use: For session-specific user data, like authentication.
//    Application (Web only):
//
//    A single bean instance is created for the entire servlet context.
//    Use: For application-wide shared resources.
//            WebSocket (WebSocket only):
//
//    A new bean instance is created for each WebSocket session.
//            Use: For WebSocket-specific session management.
//    You can specify the scope using @Scope annotation, e.g.:
//
//
//
//    Yes, in Spring, the @Service annotation marks a class as a Spring-managed bean by default. If you don't explicitly define a scope for it, the default scope is singleton. This means:
//
//    Default Behavior:
//
//    A single instance of the @Service class is created and shared across the entire Spring container for the application's lifecycle.
//    Works as Skeleton:
//
//    The @Service bean acts as a skeleton to process or manage the business logic layer of your application.
//    It is reused whenever the service is injected into other components, such as controllers or repositories.
//    When Scope Isn't Defined:
//
//    If you don't define a scope, @Service behaves as a singleton, ensuring the bean is instantiated only once.
//
//    Example:
}
