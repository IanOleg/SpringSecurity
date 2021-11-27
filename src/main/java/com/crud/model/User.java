package com.crud.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "loginName", unique = true)
    public String loginName;

    @Column(name = "password")
    public String password;

    @Column(name = "name")
    public String name;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {

    }

    public User(String name, String password, String loginName, Set<Role> roles) {

        this.name = name;
        this.password = password;
        this.loginName = loginName;
        this.roles = roles;
    }

    public User(String name, String password, String loginName) {

        this.name = name;
        this.password = password;
        this.loginName = loginName;
        this.roles = new HashSet<Role>();
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAge() {

        return loginName;
    }

    public void setAge(String loginName) {

        this.loginName = loginName;
    }

    @Override
    public String toString() {

        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + loginName +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return name;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Set<Role> getRoles() {

        return roles;
    }

    public void setRoles(Set<Role> roles) {

        this.roles = roles;
    }

    public void addRole(Role roles) {

        this.roles.add(roles);
    }

    public String getLoginName() {

        return loginName;
    }

    public void setLoginName(String loginName) {

        this.loginName = loginName;
    }

}
