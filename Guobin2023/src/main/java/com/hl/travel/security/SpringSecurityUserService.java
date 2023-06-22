package com.hl.travel.security;



import com.hl.travel.Service.LoginService;
import com.hl.travel.Service.UserService;
import com.hl.travel.entity.Permission;
import com.hl.travel.entity.Role;
import com.hl.travel.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SpringSecurityUserService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(username);

        if (user == null) {
            return null;
        }

        List<GrantedAuthority> list = new ArrayList<>();

        Set<Role> roles = user.getRoles();

        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
        return userDetails;
    }
}
