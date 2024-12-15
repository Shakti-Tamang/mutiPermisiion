package com.nextstep.multiauhtnticate;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAdminServer
@OpenAPIDefinition(info = @Info(title = "My API for Library Management System", version = "v1",description = "This API enables efficient management of library resources for colleges, allowing users to add, search, and manage book records. It supports role-based access control to ensure that only authorized users, like administrators, can add new books and perform management tasks. Designed for seamless integration, the API streamlines common library functions, enhancing accessibility and organization."))
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
