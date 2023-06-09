package com.example.demo.entity;

import com.example.demo.entity.supports.AuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User extends BaseEntity {


    @Column(length = 30, unique = true)
    private String username;

    @Column(length = 100, nullable = true)
    private String password;

    @Column(length = 40, nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "VARCHAR(50) CHARACTER SET utf8")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<ConfirmationToken> confirmationToken;
    @Lob
    @Column
    private String avatar;

    @Column(columnDefinition="tinyint(1)", nullable = false)
    private Boolean isActive;

    @Column
    private AuthProvider provider;
    @Column
    private String providerId;

    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @JoinTable(name = "tbl_user_role", joinColumns = {
            @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")})
    private Set<Role> roles;
}
