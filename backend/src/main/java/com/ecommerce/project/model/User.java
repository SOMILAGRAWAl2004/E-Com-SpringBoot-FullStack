package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String userName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Size(max = 120)
    @Column(name = "email")
    @Email
    private String email;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }


    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @ToString.Exclude
    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Cart cart;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private Set<Product> products;
    /* intuitive meaning of mappedBy :
    “I, 'User', do not have a separate column or join table for this relationship.
    If you ever want to find my products, go and check in the products table — they already store who I am using the seller_id foreign key.”

    or we can also say that the 'user' object, that is foreign key on the other side of the relation is mapped by my userId*/

    @Getter
    @Setter
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, mappedBy = "user")
//    @JoinTable(name = "user_address",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses = new ArrayList<>();

}
/* use this query in h2 database for checking the 'unique' constraints
SELECT tc.CONSTRAINT_NAME,
tc.TABLE_NAME,
kcu.COLUMN_NAME
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
ON tc.CONSTRAINT_NAME = kcu.CONSTRAINT_NAME
WHERE tc.CONSTRAINT_TYPE = 'UNIQUE';
*/