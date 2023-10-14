package ru.clickgroup.vtbmockapi.services.jwt;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.clickgroup.vtbmockapi.domain.user.UserEntity;

import java.util.Collection;

@Data
public class CustomUserDetails implements UserDetails {
    private String login;
    private String password;
    private UserEntity user;

    private String token;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(UserEntity user) {
        CustomUserDetails c = new CustomUserDetails();
        c.login = user.getEmail();
        c.password = user.getPassword();
        c.user = user;
        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
