package ru.khusyainov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "authorities",
            joinColumns = @JoinColumn(
                    name = "username",
                    referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(
                    name = "role",
                    referencedColumnName = "role"))
    private List<Role> roles;

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void setRoles(String rolesString) {
        roles = new ArrayList<>();
        Arrays.stream(rolesString.split(",")).forEach(role -> roles.add(new Role("ROLE_" + role.toUpperCase().replaceAll(" ", ""))));
    }
}