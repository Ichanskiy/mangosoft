package com.mangosoft.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@ToString
public class User extends BaseObject{

    static final String NAME = "name";
    static final String EMAIL = "email";
    static final String PASSWORD = "password";
    static final String LAST_NAME = "lastName";
    static final String ACTIVE = "active";
    static final String USER = "user";
    static final String JOIN_TABLE = "user_role";
    static final String JOIN_COLUMN_USER = "user_id";
    static final String JOIN_COLUMN_ROLE = "role_id";

    @JsonProperty
    @Column(name = EMAIL)
    private String email;

    @JsonProperty
    @Column(name = PASSWORD)
    @Length(min = 4, message = "*Your password must have at least 4 characters")
    private String password;

    @JsonProperty
    @Column(name = NAME)
    private String name;

    @JsonProperty
    @Column(name = LAST_NAME)
    private String lastName;

    @JsonProperty
    @Column(name = ACTIVE)
    private int active;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = JOIN_TABLE, joinColumns = @JoinColumn(name = JOIN_COLUMN_USER), inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_ROLE))
//    public Set<Role> roles;

}
