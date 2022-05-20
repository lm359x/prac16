package org.example.dog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "dogs")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dog {
    @Id
    private Long id;
    private String name;
    private String breed;
    @ManyToOne
    @JsonIgnore
    public User user;

}
