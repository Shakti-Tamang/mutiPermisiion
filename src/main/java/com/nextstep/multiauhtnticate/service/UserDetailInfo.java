package com.nextstep.multiauhtnticate.service;


import com.nextstep.multiauhtnticate.Model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//The UserDetailInfo class implements UserDetails, which is a core interface in Spring
//        Security. Its role is to provide user-specific information like username, password,
//        and authorities (roles) required for authentication. It converts the UserModel1 data
//        into a format that Spring Security can use for validating user credentials and
//        determining user permissions. This enables Spring Security to handle authentication and
//        authorization efficiently based on the provided user details.


public class UserDetailInfo  implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetailInfo(UserModel member) {
        username = member.getUsername();
        password = member.getPassword();
        authorities = Arrays.stream(member.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
