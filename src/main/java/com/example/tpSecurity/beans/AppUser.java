package com.example.tpSecurity.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@NoArgsConstructor
@Component
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String email ;
    @JsonIgnore
    private String password ;
    //private Role role ;
    private ArrayList<GrantedAuthority> authorities;

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUser(String email,String password,AppUserRole role)
    {
        this.email = email ;
        this.password = password ;

        ArrayList<GrantedAuthority> authorities2 = new ArrayList();
        authorities2.add(new SimpleGrantedAuthority(role.toString()));
        this.authorities=authorities2;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
