package com.nextstep.multiauhtnticate.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Courses {

    @Id
    @Schema(hidden = true)

    private String courseId;

    @Column(nullable = false)
    @Schema(required = true)
    @NotNull(message = "must not be null")
    @NotEmpty(message = "must not be empty")

    private String title;

    @Column(nullable = false)
    @Schema(required = true)
    @NotNull(message = "code must not be null")
    @NotEmpty(message = "code must not be empty")
    private String courseCode;

    @Column(nullable = false)
    @Schema(required = true)
    @NotNull(message = "Must not be null")
    @NotEmpty(message = "Must not be empty")
    private String coursediscription;

    @Column(nullable = false)
    @Schema(required = true)
    @NotEmpty(message = "Must not be empty")
    @NotNull(message = "Must not be null")
    private String modeOfDelivery;

//    A cyclic reference (also called a circular reference) occurs when two or
//    more objects reference each other in such a way that navigating their
//    relationships creates an endless loop. This can cause problems, especially
//    during JSON serialization, as the serialization process keeps traversing
//    the circular structure indefinitely.


//     Yes — both @JsonIgnoreProperties and fetch = FetchType.LAZY help in different ways, but they solve different problems:

// 🔹 1. @JsonIgnoreProperties("courseList")

// Purpose: Prevents infinite recursion (endless loop) during JSON serialization.

// Example problem without it:

// Course → has users

// User → has courseList

// Jackson tries to serialize everything → loops forever.

// ✅ With @JsonIgnoreProperties("courseList"), Jackson will skip serializing courseList when serializing User inside Course. That breaks the loop.

// 🔹 2. fetch = FetchType.LAZY

// Purpose: Controls when data is fetched from the database, not JSON recursion.

// With LAZY, users won’t be fetched until you explicitly call course.getUsers().

// Prevents unnecessary data loading (performance optimization).

// ⚠️ Important: LAZY does not stop infinite recursion by itself — it just delays loading. If Jackson tries to serialize users, it will still fetch them and can still recurse unless @JsonIgnoreProperties (or @JsonManagedReference / @JsonBackReference) is used.

// ✅ Conclusion

// @JsonIgnoreProperties (or @JsonManagedReference + @JsonBackReference) solves endless loop in JSON.

// fetch = FetchType.LAZY solves performance problems, not recursion.

// 👉 If your only problem is recursion → you must use @JsonIgnoreProperties (or the reference annotations).

    @Schema(hidden = true)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("courseList")
    private List<UserModel> usersCourse = new ArrayList<>();


}

