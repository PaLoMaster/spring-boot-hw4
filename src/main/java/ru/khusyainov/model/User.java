package ru.khusyainov.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
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

    public User() {
    }

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setRoles(String rolesString) {
        roles = new ArrayList<>();
        Arrays.stream(rolesString.split(",")).forEach(role -> roles.add(new Role("ROLE_" + role.toUpperCase().replaceAll(" ", ""))));
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + "', password='" + password + "', roles=" + roles + "}";
    }
}