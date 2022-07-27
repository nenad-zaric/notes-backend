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

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "created", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate created;
    @Column(name = "last_edit", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastEdit;

    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "locked")
    private boolean locked;

    @Column(name = "role")
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
        this.enabled = false;
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
        this.enabled = false;
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
        this.enabled = false;
        this.locked = false;
        this.role = UserRole.USER;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastname(String lastName) {
        this.lastName = lastName;
    }



    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public LocalDate getCreated() {
        return created;
    }
    public void setCreated(LocalDate created) {
        this.created = created;
    }


    public LocalDate getLastEdit() {
        return lastEdit;
    }
    public void setLastEdit(LocalDate lastEdit) {
        this.lastEdit = lastEdit;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


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


