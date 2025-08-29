package com.nextstep.multiauhtnticate.configuration;

import javax.annotation.meta.When;
import javax.swing.Spring;

import io.swagger.v3.oas.models.security.Scopes;

public class WorkFlowSpringBoot {
// Got it 👍 Let me walk you through the complete Spring Boot workflow with Spring Context, IoC, and Bean Scopes in simple terms:

// 1. Application Starts

// When you run a Spring Boot app (@SpringBootApplication), the Spring Context (a.k.a. ApplicationContext) is created.

// This context is like a big container (IoC container) where all your beans (objects managed by Spring) live.

// 2. IoC (Inversion of Control)

// Normally in Java, you create objects using new.

// With Spring IoC, Spring creates objects for you, manages their lifecycle, and injects them wherever needed.

// Example:

// @Service
// public class UserService { }


// You don’t do new UserService().

// Spring detects @Service and puts it inside the Spring Context.

// 3. Dependency Injection (DI)

// If a class depends on another bean, Spring injects it automatically.

// Example:

// @Service
// public class OrderService {
//     private final UserService userService;

//     public OrderService(UserService userService) {
//         this.userService = userService; // injected by Spring
//     }
// }

// 4. Bean Scopes

// When Spring creates a bean, its lifecycle depends on the scope:

// Singleton (default)

// Only one object per Spring Context.

// Every injection gets the same instance.

// Example: UserService shared everywhere.

// Prototype

// A new object is created every time it’s injected or requested.

// Used for short-lived or stateful objects.

// Request (for web apps)

// A new bean is created for each HTTP request.

// Session (for web apps)

// One bean per HTTP session.

// Application

// One bean shared across the whole ServletContext (like a global singleton in a web app).

// 5. Spring Context in Action

// When your program runs:

// Spring Boot scans your classes (via @ComponentScan).

// Beans (@Component, @Service, @Repository, @Controller, etc.) are registered in the Spring Context.

// Dependencies are injected (via Constructor Injection or @Autowired).

// Based on scope, Spring decides:

// Should it give the same instance (singleton)?

// Or make a new one each time (prototype)?

// Or tie it to request/session?

// 6. Workflow Example

// Let’s imagine a web request comes in:

// A user calls POST /orders.

// OrderController is invoked (bean from Spring Context).

// It calls OrderService (singleton bean injected).

// OrderService calls UserService (singleton bean injected).

// If you had a @Scope("prototype") bean, a new instance would be created each time.

// ✅ In summary:

// Spring Context = container of all beans.

// IoC = Spring creates & manages objects.

// DI = Spring injects dependencies automatically.

// Bean Scope = defines how long a bean lives and how many instances are created.

// Would you like me to also draw a simple diagram (Spring Context + Scopes) so you can visualize how everything connects when the app runs?
}
