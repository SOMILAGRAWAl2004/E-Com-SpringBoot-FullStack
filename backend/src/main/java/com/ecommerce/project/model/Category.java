package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Entity(name = "categories")
// every '@Entity' class must declare or inherit at least one '@Id' or '@EmbeddedId' property
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //"@GeneratedValue in Spring Boot is used along with @Id to automatically generate the value of the primary key field when a new entity is inserted into the database. It does not manage or generate database indexes."
    private long categoryId;

    @NotEmpty
    @Size(min = 5, message = "Category name must contain at least 5 characters")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
