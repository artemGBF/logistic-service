package ru.gbf.cartservice.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.gbf.cartservice.type.UserRole;

@Service
public class AccessService {

    public boolean clientOnly(){
        SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority(UserRole.CLIENT.name());
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.contains(authorities.contains(simpleGrantedAuthority1));
    }
}