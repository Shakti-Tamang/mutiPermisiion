package com.nextstep.multiauhtnticate.configuration;

public class Annotations {


//    @SpringBootApplication is a convenience annotation in Spring Boot that combines:
//
//    @Configuration: Marks the class as a configuration source for beans.
//    @EnableAutoConfiguration: Automatically configures Spring beans based on the dependencies.
//    @ComponentScan: Scans for components (like @Controller, @Service) in the package and sub-packages.
//    Bootstrap means starting the Spring Boot application by initializing all configurations, components, and dependencies needed to run.
//

//    Here’s a list of important Spring Boot annotations with a brief explanation for each:
//
//    Core Annotations
//    @SpringBootApplication
//    Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan to bootstrap the application.
//
//    @Component
//    Marks a class as a Spring-managed component for dependency injection.
//
//    @Service
//    Indicates a service layer class in the application.
//
//    @Repository
//    Marks a class as a Data Access Object (DAO) and provides database exception translation.
//
//    @Controller
//    Denotes a web controller handling HTTP requests in a Spring MVC application.
//
//    @RestController
//    Combines @Controller and @ResponseBody to return JSON responses directly.
//
//    @Configuration
//    Indicates that the class contains Spring bean definitions.
//
//    @Bean
//    Defines a bean that is managed by the Spring container.
//
//    @Autowired
//    Automatically injects dependencies into Spring-managed beans.
//
//    @Qualifier
//    Specifies which bean to inject when multiple beans of the same type are available.
//
//    MVC and Request Handling
//    @RequestMapping
//    Maps HTTP requests to handler methods of a controller.
//
//    @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
//    Shortcut annotations for mapping HTTP methods (GET, POST, PUT, DELETE) to specific endpoints.
//
//    @RequestParam
//    Extracts query parameters from HTTP requests.
//
//    @PathVariable
//    Binds URI template variables to method parameters.
//
//    @RequestBody
//    Maps the body of a request to a method parameter.
//
//    @ResponseBody
//    Indicates that the return value of a method should be serialized into the response body.
//
//    @CrossOrigin
//    Enables Cross-Origin Resource Sharing (CORS) for specific endpoints.
//
//    Security
//    @PreAuthorize
//    Allows method access based on expressions defined in Spring Security.
//
//    @Secured
//    Restricts access to methods based on roles.
//
//    @RolesAllowed
//    Similar to @Secured, but uses role-based restrictions as defined by JSR-250.
//
//    Data Access
//    @Entity
//    Defines a JPA entity class.
//
//    @Table
//    Specifies the table name in the database for a JPA entity.
//
//    @Id
//    Marks a field as the primary key of a JPA entity.
//
//    @GeneratedValue
//    Configures the primary key generation strategy.
//
//    @Column
//    Customizes a field’s mapping to a database column.
//
//    @OneToMany, @ManyToOne, @OneToOne, @ManyToMany
//    Defines relationships between JPA entities.
//
//    @Transactional
//    Indicates that a method or class is transactional.
//
//    Validation
//    @Valid
//    Triggers validation for an object, typically used in controllers.
//
//    @NotNull, @Size, @Email, @Pattern, etc.
//    Bean validation annotations for input validation.
//
//    Scheduling and Async
//    @Scheduled
//    Schedules a task to run at fixed intervals or cron expressions.
//
//    @EnableScheduling
//    Enables support for scheduled tasks.
//
//    @Async
//    Marks a method to be executed asynchronously.
//
//    @EnableAsync
//    Enables asynchronous processing in the application.
//
//    Others
//    @Profile
//    Activates a bean only for specific profiles (e.g., dev, prod).
//
//    @ConditionalOnProperty
//    Loads a bean based on the value of a configuration property.
//
//    @EnableCaching
//    Enables caching support in the application.
//
//    @Value
//    Injects values from properties or environment variables into fields.
//
//    @PropertySource
//    Loads properties from an external file.
//
//    @EventListener
//    Listens to application events.
//
//    @EnableWebMvc
//    Enables Spring MVC configuration in a Spring Boot application.
//
//    These annotations cover the essential aspects of Spring Boot development, from configuration and dependency injection to MVC and data access.
//
//
//
//
//
//

}
