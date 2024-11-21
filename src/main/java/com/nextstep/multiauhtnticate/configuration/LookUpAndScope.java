package com.nextstep.multiauhtnticate.configuration;

public class LookUpAndScope {
//    Using prototype scope with @Lookup in the above scenario is beneficial in cases where you need fresh, independent objects for each operation. Let's break this down:
//
//    Why Use Prototype Scope in This Scenario?
//    Avoid Shared State Issues:
//
//    In a singleton bean (default scope), a single instance of a class is shared across the application.
//    If AddBook were a singleton, the same object would be reused, causing issues like overwriting values (e.g., id, bookTitle) when multiple users try to add books concurrently.
//    Prototype scope ensures every user gets a unique AddBook instance, avoiding these conflicts.
//    Dynamic Object Creation:
//
//    The @Lookup annotation dynamically retrieves a new AddBook instance on each request, without needing manual management.
//    This is particularly useful when transient data or temporary state is involved, as in creating and persisting new book entries.
//    Scalability for Parallel Requests:
//
//    In a multi-user system, concurrent requests may need independent book objects. With prototype scope, a new object is created for each user or request without any manual intervention.
//    How It Can Be Beneficial?
//    Fresh State for Each Operation:
//
//    Each AddBook instance is fresh, ensuring data consistency and avoiding unintended overwrites.
//            Example: Two users trying to add different books won't interfere with each other because they each get their own instance.
//    Improved Maintainability:
//
//    By using @Lookup, your service code becomes clean and less coupled to object creation logic.
//    No need to manually manage instances or inject the ApplicationContext.
//    Perfect for Non-Persistent, Transient Data:
//
//    If the AddBook object includes data relevant only for the current request, such as temporary book metadata or context-specific state, prototype scope is ideal.
//    Encapsulation of Object Lifecycles:
//
//    Prototype scope abstracts away the lifecycle of objects, letting Spring manage their creation and destruction while you focus on implementing business logic.
//    Real-Life Use Cases
//    Transactional Operations (Like Adding Books):
//
//    A new book is added per user request, making prototype scope ideal because each transaction requires an independent object.
//    Report Generation:
//
//    Each report request may involve dynamically created objects with unique data, requiring a fresh instance each time.
//    Batch Processing:
//
//    In scenarios like batch imports, each record can use a prototype-scoped object to maintain isolation between different items in the batch.
//

//    Summary
//    By using prototype scope and @Lookup, your application gains:
//
//    Isolation of Data: Each user gets their own book instance.
//            Scalability: Supports high-concurrency scenarios without conflicts.
//    Simplicity: Cleaner code with dynamic object creation handled by Spring.
//    This approach ensures your "Add Book" functionality is robust, scalable, and cleanly implemented while avoiding pitfalls of shared or improperly managed state.
//
}
