package org.stroganov.security.jwt;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.stroganov.entities.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class JwtUserFactory {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getLogin(),
                user.getFullName(),
                user.getPasscodeHash(),
                user.isBlocked(),
                user.isAdmin(),
                mapToGrantedAuthorities(user)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(User user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role = user.isAdmin() ? ADMIN : USER;
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }
}
