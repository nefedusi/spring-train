package org.example.springtrain.security;

import lombok.AllArgsConstructor;
import org.example.springtrain.model.Person;
import org.example.springtrain.model.PersonRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class CustomUserPrincipal implements UserDetails {

    private final Person person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<PersonRole> roles = person.getRoles();
        for (PersonRole role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole().toString());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getLogin();
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
