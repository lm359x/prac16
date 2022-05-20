package org.example.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dog.Dog;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "users_seq", sequenceName =
            "users_sequence", allocationSize = 1)
    @GeneratedValue(generator = "users_seq", strategy =
            GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<Dog> dogs;
    public User(String firstName, String lastName){
        this.firstName=firstName;
        this.lastName = lastName;
        this.dogs = new ArrayList<>();
    }
}
