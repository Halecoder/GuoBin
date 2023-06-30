package com.hl.travel.security;


import com.hl.travel.clients.GuobinRoleClient;
import com.hl.travel.model.dao.RedisDao;
import com.hl.travel.model.pojo.Permission;
import com.hl.travel.model.pojo.Role;
import com.hl.travel.model.pojo.User;
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
public class SpringSecurityUserService implements UserDetailsService {

    @Autowired
    private GuobinRoleClient guobinRoleClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = guobinRoleClient.findUserByUserName(username);

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
