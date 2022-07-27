package com.anunnakisoftware.notes.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String name;
    private String lastName;
    private String email;
    private String password;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate created;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastEdit;

    private boolean enabled;
    private boolean locked;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {}

    public User(Long id, String username, String lastName, String email, String name, String password, LocalDate created, LocalDate lastEdit) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.name = name;
        this.password = password;
        this.created = created;
        this.lastEdit = lastEdit;
        this.enabled = true;
        this.locked = false;
        this.role = UserRole.USER;
    }

    public User(String username, String email, String name, String lastName, String password, LocalDate created, LocalDate lastEdit) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.created = created;
        this.lastEdit = lastEdit;
        this.enabled = true;
        this.locked = false;
        this.role = UserRole.USER;
    }

    public User(String username, String email, String name, String lastName, String password, String created, String lastEdit) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.created = LocalDate.parse(created, dateTimeFormatter);
        this.lastEdit = LocalDate.parse(lastEdit, dateTimeFormatter);
        this.enabled = true;
        this.locked = false;
        this.role = UserRole.USER;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastname(String lastName) {
        this.lastName = lastName;
    }


    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }
    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Column(name = "last_edit", nullable = false)
    public LocalDate getLastEdit() {
        return lastEdit;
    }
    public void setLastEdit(LocalDate lastEdit) {
        this.lastEdit = lastEdit;
    }


    @Column(name = "role")
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Column(name = "locked")
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Column(name = "enabled")
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", lastEdit=" + lastEdit +
                '}';
    }
}


